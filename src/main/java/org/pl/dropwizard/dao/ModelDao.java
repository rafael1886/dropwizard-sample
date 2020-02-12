package org.pl.dropwizard.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.pl.dropwizard.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ModelDao {
    private static final Logger log = LoggerFactory.getLogger(ModelDao.class);
    private final Jdbi jdbi;

    public ModelDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    Model create(Model model) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("insert into models(name, brand_id) " +
                        "values (:name, :brand_id) ")
                        .bind("name", model.getName())
                        .bind("brand_id", model.getBrand())
                        .executeAndReturnGeneratedKeys()
                        .mapTo(Model.class)
                        .one());
    }

    public Optional<Model> findById(Long id) {
        log.info("Find model by id " + id);
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Model.class));
            return handle.createQuery("select * from models where id = :id")
                    .bind("id", id)
                    .mapTo(Model.class)
                    .findOne();
        });
    }

    public List<Model> findAll() {
        log.info("Find all models");
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Model.class));
            return handle.createQuery("select * from models")
                    .mapTo(Model.class)
                    .list();
        });
    }
}
