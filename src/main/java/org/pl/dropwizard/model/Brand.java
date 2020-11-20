package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.pl.dropwizard.model.dto.BrandDto;

public class Brand {
  @ColumnName("id_brand")
  private Long id;

  @ColumnName("name_brand")
  private String name;

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

  public BrandDto toDto() {
    return BrandDto.builder().id(id).name(name).build();
  }
}
