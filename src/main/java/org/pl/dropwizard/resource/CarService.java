package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.dto.CarDto;
import org.pl.dropwizard.model.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

public class CarService implements CarResource {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);
    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Response create(CarDto carDto) {
        log.info("create " + carDto.toString());
        return status(CREATED)
                .entity(carDao.create(CarMapper.toEntity(carDto)))
                .build();
    }

    @Override
    public Response update(final CarDto carDto) {
        log.info("update " + carDto.toString());
        return ok(carDao.update(CarMapper.toEntity(carDto))).build();
    }

    public Response findById(final Long id) {
        log.info("find by id " + id);
        return ok(carDao.findById(id).orElseThrow(
                () -> new WebApplicationException("Car not found", NOT_FOUND)
        )).build();
    }

    public Response findAll() {
        log.info("find all");
        List<Car> cars = carDao.findAll();
        return cars.isEmpty()
                ? status(NOT_FOUND).build()
                : ok(cars).build();
    }

    @Override
    public Response delete(Long id) {
        carDao.deleteById(id);
        return noContent().build();
    }
}
