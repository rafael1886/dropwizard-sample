package org.pl.dropwizard;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;

import java.util.Optional;

public class AddressRepo {
    Jdbi jdbi = JpaConfig.getInstance().getJdbi();

    void create() {
        jdbi.useHandle(handle -> handle.createUpdate("insert into address (street, city, state, zip) values (:street, :city, :state, :zip)")
                .bind("street", "Agaty")
                .bind("city", "Warsav")
                .bind("state", "")
                .bind("zip", "22-345")
                .execute());
    }

    Optional<Address> findById(Long id) {
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Address.class));
            return handle.createQuery("select * from address where id = :id")
                    .bind("id", id)
                    .mapTo(Address.class)
                    .findFirst();
        });
    }
}
