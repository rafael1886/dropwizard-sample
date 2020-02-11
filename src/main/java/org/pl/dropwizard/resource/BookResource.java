package org.pl.dropwizard.resource;

import org.pl.dropwizard.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public interface BookResource {

    @GET
    @Path("{id}")
    public Representation<Book> findById(@PathParam("id") final Long id);

    @GET
    public List<Book> findAll();
}
