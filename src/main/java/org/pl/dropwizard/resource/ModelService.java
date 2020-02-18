package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.ModelDto;
import org.pl.dropwizard.model.mapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

public class ModelService implements ModelResource{
    private static final Logger log = LoggerFactory.getLogger(ModelService.class);
    private final ModelDao modelDao;

    public ModelService(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public Response create(ModelDto modelDto) {
        log.info("create " + modelDto.toString());
        return status(CREATED)
                .entity(ModelMapper.toDto(modelDao.create(modelDto.getName(), modelDto.getBrand())))
                .build();
    }

    @Override
    public Response update(ModelDto modelDto) {
        log.info("update " + modelDto.toString());
        return null;
    }

    @Override
    public Response findById(Long id) {
        log.info("find by id " + id);
        return ok(modelDao.findById(id).orElseThrow(
                () -> new WebApplicationException("Model not found", NOT_FOUND)
        )).build();
    }

    @Override
    public Response findById2(Long id) {
        log.info("find by id " + id);
        return ok(modelDao.findById2(id).orElseThrow(
                () -> new WebApplicationException("Model not found", NOT_FOUND)
        )).build();
    }

    @Override
    public Response findAll() {
        log.info("find all");
        List<Model> models = modelDao.findAll();
        return models.isEmpty()
                ? status(NOT_FOUND).build()
                : ok(models).build();
    }

    @Override
    public Response delete(Long id) {
        log.info("delete " + id);
        modelDao.deleteById(id);
        return noContent().build();
    }
}
