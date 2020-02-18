package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.ModelDto;

public class ModelMapper {
    public static Model toEntity(ModelDto modelDto) {
        return Model.builder()
                .id(modelDto.getId())
                .name(modelDto.getName())
                .build();
    }

    public static ModelDto toDto(Model model) {
        ModelDto modelDto = new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setName(model.getName());
//        modelDto.setBrand(model.getBrand().getId());
        return modelDto;
    }
}
