package org.pl.dropwizard.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.jdbi.v3.core.result.ResultBearing;
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

    public Optional<Car> create(Car model) {
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Car.class));
            ResultBearing resultBearing = handle.createUpdate(
                    "insert into cars(model_id, year_production, type_of_fuel, engine_capacity, color) " +
                            " values (:model_id, :year_production, :type_of_fuel, :engine_capacity, :color) ")
                    .bind("model_id", model.getModel().getId())
                    .bind("year_production", model.getYearProduction())
                    .bind("type_of_fuel", model.getTypeOfFuel())
                    .bind("engine_capacity", model.getEngineCapacity())
                    .bind("color", model.getColor())
                    .executeAndReturnGeneratedKeys();
                 return resultBearing. mapTo(Car.class)
                 .findFirst();

        });
    }

    public Car update(Car car) {
        return null;
    }

    public Optional<Car> findById(Long id) {
        Optional<Car> id1 = jdbi.withHandle(handle -> {
            handle.registerRowMapper(FieldMapper.factory(Car.class));
                    return handle.createQuery("select * from cars c left outer join models m on m.id_model = c.model_id where c.id = :id") //c left outer join models m on m.id = c.model_id
                            .bind("id", id)
                            .mapTo(Car.class)
                            .findOne();
                });
        log.info(id1.get().toString());
        return id1;
    }

    public List<Car> findAll() {
        log.info("Find all cars");
        return jdbi.withHandle(handle -> {
//            handle.registerRowMapper(ConstructorMapper.factory(Car.class));
            handle.registerRowMapper(ConstructorMapper.factory(Car.class));
            return handle.createQuery("select id, year_production, type_of_fuel, engine_capacity, color, id_model, name_model from cars")// c left outer join models m on m.id = c.model_id")
                    .mapTo(Car.class)
                    .list();
        });
    }
//
//    public List<Car> findAll() {
//        log.info("Find all cars");
//        return jdbi.withHandle(handle -> {
//            handle.registerRowMapper(BeanMapper.factory(Car.class, "c"));
//            handle.registerRowMapper(BeanMapper.factory(Model.class, "m"));
//            handle.registerRowMapper(JoinRowMapper.forTypes(Car.class, Model.class));
//            return handle.createQuery("select c.id, c.year_production, c.type_of_fuel, c.engine_capacity, c.color, m.id, m.name, m.brand_id from cars c left outer join models m on m.id = c.model_id")
//                    .mapTo(JoinRow.class)
//                    .map(a -> a.get(Car.class))
//                    .list();
//        });
//    }

    public void delete(Car car) {
        deleteById(car.getId());
    }

    public boolean deleteById(Long id) {
        int countUpdate = jdbi.withHandle(handle -> handle.createUpdate("delete from cars where id = :id")
                .bind("id", id)
                .execute());
        return countUpdate == 0 ? false : true;
    }

}
