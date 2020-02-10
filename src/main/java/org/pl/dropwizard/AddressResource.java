package org.pl.dropwizard;

import com.codahale.metrics.annotation.Timed;
import org.checkerframework.checker.units.qual.Time;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed("ADMIN")
public class AddressResource {
    public final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @POST
    @Time
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAddress(Address address) {
        addressService.create(address);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Timed
    public Representation<List<Address>> findAddress() {
        return new Representation<>(HttpStatus.OK_200, addressService.findAll());
    }

    @GET
    @Timed
    @Path("{id}")
    public Representation<Address> getAddress(@PathParam("id") final Long id) {
        return new Representation<>(HttpStatus.OK_200, addressService.findById(id));
    }
}
