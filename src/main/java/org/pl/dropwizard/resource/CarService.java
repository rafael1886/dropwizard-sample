package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.CarDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Set;
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
    final Car car = carDto.toEntity();
    car.setModel(findModel(carDto.getModel()));
    return status(CREATED).entity(carDao.create(car).toDto()).build();
  }

  @Override
  public Response update(final CarDto carDto, final Long id) {
    log.info("update " + carDto.toString());
    final Car car = carDto.toEntity();
    car.setModel(findModel(carDto.getModel()));
    final boolean success = carDao.update(car, id);
    return success ? ok().build() : Response.status(NOT_FOUND).build();
  }

  @Override
  public Response delete(final Long id) {
    log.info("delete " + id);
    final boolean success = carDao.deleteById(id);
    return success ? noContent().build() : Response.status(NOT_FOUND).build();
  }

  @Override
  public Response findById(final Long id) {
    log.info("find by id " + id);
    return ok(carDao
            .findById(id)
            .orElseThrow(() -> new WebApplicationException("Car not found, id = " + id, NOT_FOUND))
            .toDto())
        .build();
  }

  @Override
  public Response findAll() {
    log.info("find all");
    Set<Car> cars = carDao.findAll();
    return cars.isEmpty()
        ? status(NOT_FOUND).build()
        : ok(cars.stream().map(Car::toDto).collect(Collectors.toList())).build();
  }

  @Override
  public Response findAllAll() {
    log.info("fina all entity");
    return ok(carDao.findAllWithModelAndBrand()).build();
  }

  private Model findModel(Long modelId) {
    return modelDao
        .findById(modelId)
        .orElseThrow(() -> new WebApplicationException("Model not found", NOT_FOUND));
  }
}
