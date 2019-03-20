package com.luke.force.the.use.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.luke.force.the.use.api.Genre;
import com.luke.force.the.use.db.GenreRepository;
import com.luke.force.the.use.dto.GenreDTO;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("genres")
@Api(value = "/genres")
@Produces(MediaType.APPLICATION_JSON)
public class GenreResource
{
    private final GenreRepository genreRepository;

    public GenreResource(GenreRepository genreRepository)
    {
        this.genreRepository = genreRepository;
    }
    
    @GET
    @UnitOfWork
    @ApiOperation(value = "Finds all Genres in database",
            notes = "Preview of Genre list, useful when adding or updating Movie",
            response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of genres") })
    public List<GenreDTO> getGenres()
    {
        List<Genre> dbGenries = genreRepository.findAll();
        
        return dbGenries.stream()
                        .map(g -> mapGenreToDto(g))
                        .collect(Collectors.toList());
    }
    
    //
    // privates
    //
    
    private GenreDTO mapGenreToDto(Genre g)
    {
        return GenreDTO.builder()
                       .id(g.getId())
                       .name(g.getName())
                       .build();
    }
}
