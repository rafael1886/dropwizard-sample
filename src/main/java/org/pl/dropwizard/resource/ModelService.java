package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.BrandDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.ModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

public class ModelService implements ModelResource {
  private static final Logger log = LoggerFactory.getLogger(ModelService.class);
  private final ModelDao modelDao;
  private final BrandDao brandDao;

  public ModelService(ModelDao modelDao, BrandDao brandDao) {
    this.modelDao = modelDao;
    this.brandDao = brandDao;
  }

  @Override
  public Response create(final ModelDto modelDto, final Long brandId) {
    log.info("create " + modelDto.toString());
    final Model model = findBrand(modelDto, brandId);
    return status(CREATED).entity(modelDao.create(model).toDto()).build();
  }

  @Override
  public Response update(final ModelDto modelDto, final Long id, final Long brandId) {
    log.info("update " + modelDto.toString());
    final Model model = findBrand(modelDto, brandId);
    final boolean success = modelDao.update(model, id);
    return success ? ok().build() : status(NOT_FOUND).build();
  }

  @Override
  public Response findById(final Long id) {
    log.info("find by id " + id);
    return ok(modelDao
            .findById(id)
            .orElseThrow(
                () -> new WebApplicationException("Model not found, id = " + id, NOT_FOUND))
            .toDto())
        .build();
  }

  @Override
  public Response findAll() {
    log.info("find all");
    final Set<ModelDto> models =
        modelDao.findAll().stream().map(Model::toDto).collect(Collectors.toSet());
    return models.isEmpty() ? status(NOT_FOUND).build() : ok(models).build();
  }

  @Override
  public Response delete(final Long id) {
    log.info("delete " + id);
    final boolean success = modelDao.deleteById(id);
    return success ? noContent().build() : status(NOT_FOUND).build();
  }

  private Model findBrand(ModelDto modelDto, Long brandId) {
    final var model = modelDto.toEntity();
    model.setBrand(
        brandDao
            .findById(brandId)
            .orElseThrow(() -> new WebApplicationException("Brand not found", NOT_FOUND)));
    return model;
  }
}
