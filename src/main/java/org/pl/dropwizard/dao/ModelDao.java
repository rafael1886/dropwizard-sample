package org.pl.dropwizard.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.pl.dropwizard.model.Model;

import java.util.List;
import java.util.Optional;

public class ModelDao {
    private final Jdbi jdbi;

    public ModelDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Model create(Model model) {
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Model.class));
            return handle.createUpdate("insert into models(name_model, brand_id) values (:name, :brand_id) ")
                    .bind("name", model.getName())
                    .bind("brand_id", model.getBrand().getId())
                    .executeAndReturnGeneratedKeys()
                    .mapTo(Model.class)
                    .one();
        });
    }

    public boolean update(Model model, Long id) {
        return 1 == jdbi.withHandle(handle -> handle.createUpdate("update models set name_model = :name, brand_id = :brand_id where id_model = :id ")
                .bind("name", model.getName())
                .bind("brand_id", model.getBrand().getId())
                .bind("id", id)
                .execute());
    }

    public boolean deleteById(Long id) {
        return 1 == jdbi.withHandle(handle -> handle.createUpdate("delete from models where id_model = :id")
                .bind("id", id)
                .execute());
    }

    public Optional<Model> findById(Long id) {
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Model.class));
            return handle.createQuery("select * from models m left join brands b on b.id_brand = m.brand_id where m.id_model = :id")
                    .bind("id", id)
                    .mapTo(Model.class)
                    .findFirst();
        });
    }

    public List<Model> findAll() {
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Model.class));
            return handle.createQuery("select * from models m left join brands b on b.id_brand = m.brand_id")
                    .mapTo(Model.class)
                    .list();
        });
    }
}