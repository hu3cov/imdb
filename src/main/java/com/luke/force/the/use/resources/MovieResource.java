package com.luke.force.the.use.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.jersey.params.LongParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("movies")
@Api(value = "/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource
{
    @GET
    @Path("{id}")
    @ApiOperation(value = "View single Movie by given ID")
    public void getSingleMovie(@PathParam("id") LongParam id)
    {
        // TODO implement
    }
}
