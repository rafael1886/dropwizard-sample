package org.pl.dropwizard.model.mapper;

import org.pl.dropwizard.model.Car;
import org.pl.dropwizard.model.dto.CarDto;

public class CarMapper {
    public static Car toEntity(final CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setEngineCapacity(carDto.getEngineCapacity());
        car.setTypeOfFuel(carDto.getTypeOfFuel());
        car.setYearProduction(carDto.getYearProduction());
        car.setColor(carDto.getColor());
        return car;
    }

    public static CarDto toDto(final Car car) {
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