package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.dto.ModelDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/model")
@Produces(MediaType.APPLICATION_JSON)
public interface ModelResource {

  @POST
  Response create(final ModelDto modelDto, @PathParam("brand_id") final Long brandId);

  @PUT
  @Path("{id}")
  Response update(
      final ModelDto modelDto,
      @PathParam("id") final Long id,
      @PathParam("brand_id") final Long brandId);

  @GET
  @Path("{id}")
  Response findById(@PathParam("id") final Long id);

  @GET
  Response findAll();

  @DELETE
  @Path("{id}")
  Response delete(@PathParam("id") final Long id);
}
