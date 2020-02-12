package org.pl.dropwizard.resource;

import org.eclipse.jetty.http.HttpStatus;
import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.model.CarDto;
import org.pl.dropwizard.model.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

public class CarService implements CarResource {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);
    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Response create(CarDto carDto) {
        log.info("create " + carDto.toString());
        return Response.status(Response.Status.CREATED).entity(carDao.create(CarMapper.toEntity(carDto))).build();
    }

    public Response findById(Long id) {
        return ok(carDao.findById(id).orElseThrow(
                () -> new WebApplicationException("Car not found", HttpStatus.NOT_FOUND_404)
        )).build();
    }

    public Response findAll() {
        return carDao.findAll().isEmpty() ? Response.status(Response.Status.NOT_FOUND).build() : ok(carDao.findAll()).build();
    }
}
