package org.pl.dropwizard.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.pl.dropwizard.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CarDao {
    private static final Logger log = LoggerFactory.getLogger(CarDao.class);
    private final Jdbi jdbi;

    public CarDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Car create(Car model) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "insert into cars(model_id, year_production, type_of_fuel, engine_capacity, color) " +
                                " values (:model_id, :year_production, :type_of_fuel, :engine_capacity, :color) ")
                        .bind("model_id", model.getModel().getId())
                        .bind("year_production", model.getYearProduction())
                        .bind("type_of_fuel", model.getTypeOfFuel())
                        .bind("engine_capacity", model.getEngineCapacity())
                        .bind("color", model.getColor())
                        .execute());
//                        .executeAndReturnGeneratedKeys()
//                        .mapTo(Car.class)
//                        .one());
        return null;
    }

    public Optional<Car> findById(Long id) {
        log.info("Find model by id " + id);
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Car.class));
            return handle.createQuery("select * from cars where id = :id")
                    .bind("id", id)
                    .mapTo(Car.class)
                    .findOne();
        });
    }

    public List<Car> findAll() {
        log.info("Find all cars");
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Car.class));
            return handle.createQuery("select * from cars")
                    .mapTo(Car.class)
                    .list();
        });
    }
}
