package org.pl.dropwizard;

import com.codahale.metrics.annotation.Timed;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed("ADMIN")
public class AddressResource {
    public final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GET
    @Timed
    public Representation<List<Address>> getAddress() {
        return new Representation<List<Address>>(HttpStatus.OK_200, addressService.findAll());
    }

    @GET
    @Timed
    @Path("{id}")
    public Representation<Address> getAddress(@PathParam("id") final long id) {
        return new Representation<Address>(HttpStatus.OK_200, addressService.findById(id));
    }
}
