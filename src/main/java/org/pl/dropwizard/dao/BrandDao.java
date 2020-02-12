package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Brand;

import java.util.Optional;
import java.util.Set;

public interface BrandDao {
    @SqlUpdate("insert into brands (name) values (:name)")
    void create();

    @SqlQuery("select * from brands where id = :id")
    @RegisterBeanMapper(Brand.class)
    Optional<Brand> findById(@Bind("id") Long id);

    @SqlQuery("select * from brands")
    @RegisterBeanMapper(Brand.class)
    Set<Brand> findAll();
}
