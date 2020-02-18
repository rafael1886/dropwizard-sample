package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.dto.CarDto;

public class CarMapper {

    public static Car toEntity(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .engineCapacity(carDto.getEngineCapacity())
                .typeOfFuel(carDto.getTypeOfFuel())
                .yearProduction(carDto.getYearProduction())
                .color(carDto.getColor())
                .build();
    }

    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .engineCapacity(car.getEngineCapacity())
                .typeOfFuel(car.getTypeOfFuel())
                .yearProduction(car.getYearProduction())
                .color(car.getColor())
                .modelId(car.getModel() == null ? null : car.getModel().getId())
                .build();
    }
}
