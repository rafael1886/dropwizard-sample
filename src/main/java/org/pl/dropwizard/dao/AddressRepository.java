package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    @SqlUpdate("insert into address (street, city, state, zip) values (:street, :city, :state, :zip)")
    void create();

//    @SqlUpdate("insert into something (id, name) values (:id, :name)")
//    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select * from address where id = :id")
    @RegisterBeanMapper(Address.class)
    Optional<Address> findById(@Bind("id") Long id);

    @SqlQuery("select * from address")
    @RegisterBeanMapper(Address.class)
    List<Address> findAll();
}
