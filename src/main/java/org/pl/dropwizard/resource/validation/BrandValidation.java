package org.pl.dropwizard.resource.validation;

import org.pl.dropwizard.config.Validation;
import org.pl.dropwizard.model.dto.BrandDto;

import java.util.Objects;
import java.util.Set;

public class BrandValidation {
  public Set<String> validateBrand(final BrandDto brandDto) {
    Validation<BrandDto> validation = new Validation<>();
    vNameNonNull(validation);
    return validation.result(brandDto);
  }

  private void vNameNonNull(Validation<BrandDto> validation) {
    validation.from(v -> Objects.nonNull(v.getName()), "Brand's name cannot be null");
  }
}
