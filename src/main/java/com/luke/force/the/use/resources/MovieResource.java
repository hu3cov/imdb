package com.luke.force.the.use.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.luke.force.the.use.api.Movie;
import com.luke.force.the.use.db.ActorRepository;
import com.luke.force.the.use.db.GenreRepository;
import com.luke.force.the.use.db.MovieRepository;
import com.luke.force.the.use.dto.ActorDTO;
import com.luke.force.the.use.dto.GenreDTO;
import com.luke.force.the.use.dto.MovieDTO;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("movies")
@Api(value = "/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource
{
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    
    public MovieResource(MovieRepository movieRepository, GenreRepository genreRepository, ActorRepository actorRepository)
    {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }
    
    @GET
    @Path("{id}")
    @UnitOfWork
    @ApiOperation(value = "View single Movie by given ID")
    public MovieDTO getSingleMovie(@PathParam("id") LongParam id)
    {
        Movie movie = movieRepository.findById(id.get())
                                     .orElseThrow(() -> new NotFoundException("No such movie."));

        return mapMovieToDto(movie);
    }
    
    @GET
    @UnitOfWork
    public List<MovieDTO> getMoviesWithFilters(@DefaultValue("1888") @QueryParam("release-year") Integer releaseYear,
                                               @DefaultValue("2147483647") @QueryParam("duration") Integer duration,
                                               @QueryParam("actorId") Long actorId) 
    {
        return movieRepository.filterMovies(releaseYear, duration, actorId).stream()
                                                                           .map(m -> mapMovieToDto(m))
                                                                           .collect(Collectors.toList());

    }
    
    @DELETE
    @Path("{id}")
    @UnitOfWork
    public void removeMovie(@PathParam("id") LongParam id)
    {
        Movie movie = movieRepository.findById(id.get())
                .orElseThrow(() -> new NotFoundException("No such movie."));
        
        movieRepository.delete(movie);
    }
    
    @POST
    @UnitOfWork
    @ApiOperation(value = "Add new Movie to database", response = Movie.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully added new Movie"),
    })
    public MovieDTO createMovie(MovieDTO movieDTO)
    {
        Movie movie = new Movie();
        mapDtoToMovie(movie, movieDTO);
        
        return mapMovieToDto(movieRepository.create(movie));
    }
    
    @PUT
    @UnitOfWork
    @ApiOperation(value = "Update existing Movie data", response = Movie.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated movie"),
    })
    public MovieDTO updateMovie(MovieDTO movieDTO)
    {
        if(movieDTO.getId() == null)
            throw new BadRequestException("Id of movie has to be given.");
        
        Movie movie = movieRepository.findById(movieDTO.getId())
                                     .orElseThrow(() -> new NotFoundException("No such movie."));
        mapDtoToMovie(movie, movieDTO);

        return mapMovieToDto(movieRepository.update(movie));
    }
    
    //
    // privates
    //
    
    private MovieDTO mapMovieToDto(Movie m)
    {
        m.getGenres().size();//eager loading
        m.getActors().size();//eager loading
        return MovieDTO.builder()
                    .id(m.getId())
                    .name(m.getName())
                    .description(m.getStoryline())
                    .releaseDate(m.getReleaseDate().toString())
                    .duration(m.getDuration())
                    .genres(m.getGenres().stream()
                                         .map(g -> GenreDTO.builder()
                                                           .id(g.getId())
                                                           .name(g.getName())
                                                           .build())
                                         .collect(Collectors.toList()))
                    .actors(m.getActors().stream()
                                         .map(a -> ActorDTO.builder()
                                                           .id(a.getId())
                                                           .fullName(a.getName() + " " + a.getSurname())
                                                           .birthDate(a.getBirthDate() != null ? a.getBirthDate().toString() : null)
                                                           .build())
                                         .collect(Collectors.toList()))
                    .build();
    }
    
    private Movie mapDtoToMovie(Movie movie, MovieDTO movieDTO)
    {
        movie.setName(movieDTO.getName());
        movie.setStoryline(movieDTO.getDescription());
        movie.setReleaseDate(LocalDate.parse(movieDTO.getReleaseDate()));
        movie.setDuration(movieDTO.getDuration());
        
        Set<Long> genresIds = movieDTO.getGenres().stream()
                                                  .map(g -> g.getId())
                                                  .collect(Collectors.toSet());
        movie.setGenres(genreRepository.findGenresByIds(genresIds));
        
        Set<Long> actorsIds = movieDTO.getActors().stream()
                                      .map(a -> a.getId())
                                      .collect(Collectors.toSet());
        movie.setActors(actorRepository.findActorsByIds(actorsIds));
        
        return movie;
    }
}
