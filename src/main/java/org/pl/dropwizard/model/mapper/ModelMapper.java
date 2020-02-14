package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Brand;
import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.ModelDto;

public class ModelMapper {
    public static Model toEntity(ModelDto modelDto) {
        Brand brand = new Brand();
        brand.setId(modelDto.getId());

        Model model = new Model();
        model.setId(modelDto.getId());
        model.setName(modelDto.getName());
        model.setBrand(brand);
        return model;
    }
}
