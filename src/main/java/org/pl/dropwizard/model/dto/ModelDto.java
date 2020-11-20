package org.pl.dropwizard.model.dto;

import org.pl.dropwizard.model.Brand;
import org.pl.dropwizard.model.Model;

public class ModelDto {
  private Long id;
  private String name;
  private Long brandId;

  public static Builder builder() {
    return new Builder();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Long getBrandId() {
    return brandId;
  }

  @Override
  public String toString() {
    return "ModelDto{" + "id=" + id + ", name='" + name + '\'' + ", brandId=" + brandId + '}';
  }

  public static class Builder {
    private final ModelDto modelDto = new ModelDto();

    public Builder id(Long id) {
      modelDto.id = id;
      return this;
    }

    public Builder name(String name) {
      modelDto.name = name;
      return this;
    }

    public Builder brand(Long brandId) {
      modelDto.brandId = brandId;
      return this;
    }

    public ModelDto build() {
      return modelDto;
    }
  }

  public Model toEntity() {
    Model model = new Model();
    model.setId(id);
    model.setName(name);
    Brand brand = new Brand();
    brand.setId(brandId);
    model.setBrand(brand);
    return model;
  }
}
