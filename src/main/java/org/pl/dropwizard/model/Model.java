package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.pl.dropwizard.model.dto.ModelDto;

import static java.util.Objects.nonNull;

public class Model { // } extends BaseEntity {
  @ColumnName("id_model")
  private Long id;

  @ColumnName("name_model")
  private String name;

  @Nested
  @ColumnName("brand_id")
  private Brand brand;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  @Override
  public String toString() {
    return "Model{" + "name='" + name + '\'' + ", brand=" + brand + '}';
  }

  public ModelDto toDto() {
    final var brandDto = nonNull(brand) ? brand.getId() : null;
    return ModelDto.builder().id(id).name(name).brand(brandDto).build();
  }
}
