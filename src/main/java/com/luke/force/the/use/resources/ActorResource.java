package com.luke.force.the.use.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.luke.force.the.use.api.Actor;
import com.luke.force.the.use.db.ActorRepository;
import com.luke.force.the.use.dto.ActorDTO;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("actors")
@Api(value = "/actors")
@Produces(MediaType.APPLICATION_JSON)
public class ActorResource
{
    private final ActorRepository actorRepository;

    public ActorResource(ActorRepository actorRepository)
    {
        this.actorRepository = actorRepository;
    }

    @GET
    @UnitOfWork
    @ApiOperation(value = "Finds all Actors in database",
            notes = "Preview of Actor list, useful when adding or updating Movie",
            response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of actors") })
    public List<ActorDTO> getActors()
    {
        List<Actor> dbActors = actorRepository.findAll();

        return dbActors.stream()
                       .map(a -> mapActorToDto(a))
                       .collect(Collectors.toList());
    }
    
    //
    // privates
    //
    
    private ActorDTO mapActorToDto(Actor a)
    {
        return ActorDTO.builder()
                       .id(a.getId())
                       .fullName(a.getName() + " " + a.getSurname())
                       .birthDate(a.getBirthDate() != null ? a.getBirthDate().toString() : null)
                       .build();
    }
}
