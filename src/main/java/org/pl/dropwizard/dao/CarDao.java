package org.pl.dropwizard.dao;

import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.pl.dropwizard.model.Car;

import java.util.Optional;
import java.util.Set;

public interface CarDao {

    @SqlUpdate("insert into cars(model_id, year_production, type_of_fuel, engine_capacity, color) " +
            " values (:model.id, :yearProduction, :typeOfFuel, :engineCapacity, :color)")
    @GetGeneratedKeys
    @RegisterFieldMapper(Car.class)
    Car create(@BindBean Car model);

    @SqlUpdate("update cars set model_id = :model.id, year_production = :yearProduction," +
            " type_of_fuel = :typeOfFuel, engine_capacity = :engineCapacity, color = :color" +
            " where id_car = :id")
    boolean update(@BindBean Car car, @Bind("id") Long id);

    @SqlUpdate("delete from cars where id_car = :id")
    boolean deleteById(@Bind("id") Long id);

    @SqlQuery("select * from cars c left join models m on m.id_model = c.model_id where c.id_car = :id")
    @RegisterFieldMapper(Car.class)
    Optional<Car> findById(@Bind("id") Long id);

    @SqlQuery("select * from cars c left join models m on m.id_model = c.model_id")
    @RegisterFieldMapper(Car.class)
    Set<Car> findAll();

    @SqlQuery("select * from cars c left join models m on m.id_model = c.model_id " +
            " left join brands b on m.brand_id = b.id_brand")
    @RegisterFieldMapper(Car.class)
    Set<Car> findAllWithModelAndBrand();
}