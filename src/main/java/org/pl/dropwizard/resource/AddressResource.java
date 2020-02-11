package org.pl.dropwizard.resource;

import com.codahale.metrics.annotation.Timed;
import org.checkerframework.checker.units.qual.Time;
import org.pl.dropwizard.model.Address;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed("ADMIN")
public interface AddressResource {

    @POST
    @Time
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    Response create(Address address);

    @GET
    @Timed
    Representation<List<Address>> findAll();

    @GET
    @Timed
    @Path("{id}")
    Representation<Address> findById(@PathParam("id") final Long id);
}
