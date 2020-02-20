package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.ModelDto;

public class ModelMapper {
    public static Model toEntity(ModelDto modelDto) {
        Model model = new Model();
        model.setId(modelDto.getId());
        model.setName(modelDto.getName());
        return model;
    }

    public static ModelDto toDto(Model model) {
        return ModelDto.builder()
                .id(model.getId())
                .name(model.getName())
                .brand(model.getBrand() == null ? null : model.getBrand().getId())
                .build();
    }
}
