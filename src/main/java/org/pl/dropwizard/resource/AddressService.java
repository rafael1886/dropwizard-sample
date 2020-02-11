package org.pl.dropwizard.resource;

import org.eclipse.jetty.http.HttpStatus;
import org.pl.dropwizard.dao.AddressRepo;
import org.pl.dropwizard.model.Address;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class AddressService implements AddressResource {
    private AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Response create(Address address) {
        addressRepo.create();
        return Response.status(Response.Status.CREATED).build();
    }

    public Representation<Address> findById(Long id) {
        return new Representation<>(HttpStatus.OK_200, addressRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Address not found", HttpStatus.NOT_FOUND_404)
        ));
    }

    public Representation<List<Address>> findAll() {
        return new Representation<>(HttpStatus.OK_200, addressRepo.findAll());
    }
}
