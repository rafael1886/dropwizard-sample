package org.pl.dropwizard;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import java.util.List;

public class AddressService {
    private AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public void create(Address address) {
        addressRepo.create();
    }

    public Address findById(Long id) {
        return addressRepo.findById(id).orElseThrow(
                () -> new WebApplicationException("Address not found", HttpStatus.NOT_FOUND_404)
        );
    }

    public List<Address> findAll() {
        return addressRepo.findAll();
    }
}
