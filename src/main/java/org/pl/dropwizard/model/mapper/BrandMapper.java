package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Brand;
import org.pl.dropwizard.model.dto.BrandDto;

public class BrandMapper {
  public static Brand toEntity(final BrandDto brandDto) {
    Brand brand = new Brand();
    brand.setId(brandDto.getId());
    brand.setName(brandDto.getName());
    return brand;
  }

  public static BrandDto toDto(final Brand brand) {
    return BrandDto.builder().id(brand.getId()).name(brand.getName()).build();
  }
}
