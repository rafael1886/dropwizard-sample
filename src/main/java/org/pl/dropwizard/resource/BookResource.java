package org.pl.dropwizard.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public interface BookResource {

    @GET
    @Path("{id}")
    Response findById(@PathParam("id") final Long id);

    @GET
    Response findAll();
}
