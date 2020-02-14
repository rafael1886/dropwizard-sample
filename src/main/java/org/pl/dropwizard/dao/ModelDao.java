package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelDao {
    @SqlUpdate("insert into models(name, brand_id) values (name, brand_id) ")
    @GetGeneratedKeys()
    @RegisterBeanMapper(Model.class)
    Model create(String name, Long brand_id);

    @SqlQuery("select * from models where id = :id")
    @RegisterBeanMapper(Model.class)
    Optional<Model> findById(Long id);

    @SqlQuery("select * from models")
    @RegisterBeanMapper(Model.class)
    List<Model> findAll();

    @SqlUpdate("delete from models where id = :id")
    void deleteById(Long id);
}
