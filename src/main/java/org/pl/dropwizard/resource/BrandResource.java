package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.dto.BrandDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/brand")
@Produces(MediaType.APPLICATION_JSON)
public interface BrandResource {

    @POST
    Response create(final BrandDto brandDto);

    @PUT
    @Path("{id}")
    Response update(final BrandDto brandDto, @PathParam("id") final Long id);

    @GET
    @Path("{id}")
    Response findById(@PathParam("id") final Long id);

    @GET
    Response findAll();

    @DELETE
    @Path("{id}")
    Response delete(@PathParam("id") final Long id);
}
