package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.dto.CarDto;
import org.pl.dropwizard.model.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

public class CarService implements CarResource {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);
    private final CarDao carDao;
    private final ModelDao modelDao;

    public CarService(CarDao carDao, ModelDao modelDao) {
        this.carDao = carDao;
        this.modelDao = modelDao;
    }

    @Override
    public Response create(final CarDto carDto) {
        log.info("create " + carDto.toString());
        return status(CREATED)
                .entity(carDao.create(CarMapper.toEntity(carDto)))
                .build();
    }

    @Override
    public Response update(final CarDto carDto) {
        log.info("update " + carDto.toString());
        final Car car = setModelInCar(carDto);
        return ok(CarMapper.toDto(carDao.create(car).get())).build();
    }

    @Override
    public Response delete(final Long id) {
        carDao.deleteById(id);
        return noContent().build();
    }

    @Override
    public Response findById(final Long id) {
        log.info("find by id " + id);
        return ok(CarMapper.toDto(carDao.findById(id).orElseThrow(
                () -> new WebApplicationException("Car not found, id = " + id, NOT_FOUND))
        )).build();
    }

    @Override
    public Response findAll() {
        log.info("find all");
        List<Car> cars = carDao.findAll();
        return cars.isEmpty()
                ? status(NOT_FOUND).build()
                : ok(cars.stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList())).build();
    }


    private Car setModelInCar(final CarDto carDto) {
        final Car car = CarMapper.toEntity(carDto);
        car.setModel(modelDao.findById(carDto.getId()).orElseThrow(
                () -> new WebApplicationException("Model not found", NOT_FOUND)
        ));
        return car;
    }
}
