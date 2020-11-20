package org.pl.dropwizard.resource;

import org.pl.dropwizard.dao.BrandDao;
import org.pl.dropwizard.model.Brand;
import org.pl.dropwizard.model.dto.BrandDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

public class BrandService implements BrandResource {
  private static final Logger log = LoggerFactory.getLogger(BrandService.class);
  private final BrandDao brandDao;

  public BrandService(BrandDao brandDao) {
    this.brandDao = brandDao;
  }

  @Override
  public Response create(final BrandDto brandDto) {
    log.info("create " + brandDto.toString());
    return status(CREATED).entity(brandDao.create(brandDto.toEntity()).toDto()).build();
  }

  @Override
  public Response update(final BrandDto brandDto, final Long id) {
    log.info("update " + brandDto.toString());
    final boolean success = brandDao.update(brandDto.toEntity(), id);
    log.info("uu " + success);
    return success ? ok().build() : status(NOT_FOUND).build();
  }

  @Override
  public Response findById(final Long id) {
    log.info("find by id " + id);
    return ok(brandDao
            .findById(id)
            .orElseThrow(() -> new WebApplicationException("Brand not found", NOT_FOUND)))
        .build();
  }

  @Override
  public Response findAll() {
    log.info("find all ");
    return ok(brandDao.findAll().stream().map(Brand::toDto).collect(Collectors.toSet())).build();
  }

  @Override
  public Response delete(final Long id) {
    log.info("delete " + id);
    final boolean success = brandDao.deleteById(id);
    log.info("is dletete  " + success);
    return success ? noContent().build() : status(NOT_FOUND).build();
  }
}
