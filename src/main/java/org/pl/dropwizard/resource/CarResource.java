package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.dto.CarDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
@Produces(MediaType.APPLICATION_JSON)
public interface CarResource {

    @POST
    Response create(final CarDto carDto);

    @PUT
    @Path("{id}")
    Response update(final CarDto carDto);

    @GET
    @Path("{id}")
    Response findById(@PathParam("id") final Long id);

    @GET
    Response findAll();

    @DELETE
    @Path("delete/{id}")
    Response delete(@PathParam("id") final Long id);
}
