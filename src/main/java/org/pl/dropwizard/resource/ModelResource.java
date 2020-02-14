package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.dto.ModelDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/model")
@Produces(MediaType.APPLICATION_JSON)
public interface ModelResource {

    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
    Response create(final ModelDto modelDto);

    @PUT
    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_JSON})
    Response update(final ModelDto modelDto);

    @GET
    @Path("{id}")
    Response findById(@PathParam("id") final Long id);

    @GET
    Response findAll();

    @DELETE
    @Path("delete/{id}")
    Response delete(@PathParam("id") final Long id);
}
