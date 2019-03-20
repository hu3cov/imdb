package com.luke.force.the.use.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.luke.force.the.use.api.Movie;
import com.luke.force.the.use.db.MovieRepository;
import com.luke.force.the.use.dto.ActorDTO;
import com.luke.force.the.use.dto.GenreDTO;
import com.luke.force.the.use.dto.MovieDTO;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("movies")
@Api(value = "/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource
{
    private final MovieRepository movieRepository;
    
    public MovieResource(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }
    
    @GET
    @Path("{id}")
    @ApiOperation(value = "View single Movie by given ID")
    public void getSingleMovie(@PathParam("id") LongParam id)
    {
        // TODO implement
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
}
