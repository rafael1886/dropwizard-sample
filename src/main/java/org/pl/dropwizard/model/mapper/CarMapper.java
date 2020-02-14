package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.Model;
import org.pl.dropwizard.model.dto.CarDto;

public class CarMapper {

    public static Car toEntity(CarDto carDto) {
        Model model = new Model();
        model.setId(carDto.getId());
        carDto.toString();
        return Car.builder()
                .id(carDto.getId())
                .model(model)
                .engineCapacity(carDto.getEngineCapacity())
                .typeOfFuel(carDto.getTypeOfFuel())
                .yearProduction(carDto.getYearProduction())
                .color(carDto.getColor())
                .build();
    }
}
