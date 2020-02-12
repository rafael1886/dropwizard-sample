package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.CarDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
public interface CarResource {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    Response create(CarDto carDto);

    @GET
    @Path("{id}")
    Response findById(@PathParam("id") final Long id);

    @GET
    Response findAll();
}
