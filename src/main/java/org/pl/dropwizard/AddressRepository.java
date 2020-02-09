package org.pl.dropwizard;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AddressRepository {
    @SqlUpdate("insert into address (street, city, state, zip) values (:street, :city, :state, :zip)")
    void create();

//    @SqlUpdate("insert into something (id, name) values (:id, :name)")
//    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select * from address where id = :id")
    Address findById(@Bind("id") Long id);

    @SqlQuery("select * from address")
    List<Address> findAll();
}
