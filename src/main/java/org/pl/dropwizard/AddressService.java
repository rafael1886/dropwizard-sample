package org.pl.dropwizard;

import org.jdbi.v3.sqlobject.CreateSqlObject;

import java.util.List;
import java.util.Optional;

public abstract class AddressService {
    private final AddressRepo addressRepo;

    @CreateSqlObject
    abstract AddressRepository addressRepository();

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public void create(Address address) {
        addressRepo.create();
    }

    public Address findById(Long id) {
        return addressRepository().findById(id);
    }

    public List<Address> findAll() {
        return addressRepository().findAll();
    }
}
