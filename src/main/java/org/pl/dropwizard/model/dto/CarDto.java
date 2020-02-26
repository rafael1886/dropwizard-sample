package org.pl.dropwizard.model.dto;

import org.pl.dropwizard.model.enums.TypeOfFuel;

public class CarDto {
    private Long id;
    private Long model;
    private Integer yearProduction;
    private TypeOfFuel typeOfFuel;
    private Integer engineCapacity; //cm3
    private String color;

    public Long getId() {
        return id;
    }

    public Long getModel() {
        return model;
    }

    public Integer getYearProduction() {
        return yearProduction;
    }

    public TypeOfFuel getTypeOfFuel() {
        return typeOfFuel;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public String getColor() {
        return color;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final CarDto carDto = new CarDto();

        public Builder id(Long id) {
            carDto.id = id;
            return this;
        }

        public Builder modelId(Long model) {
            carDto.model = model;
            return this;
        }

        public Builder yearProduction(Integer yearProduction) {
            carDto.yearProduction = yearProduction;
            return this;
        }

        public Builder typeOfFuel(TypeOfFuel typeOfFuel) {
            carDto.typeOfFuel = typeOfFuel;
            return this;
        }

        public Builder engineCapacity(Integer engineCapacity) {
            carDto.engineCapacity = engineCapacity;
            return this;
        }

        public Builder color(String Color) {
            carDto.color = Color;
            return this;
        }

        public CarDto build() {
            return carDto;
        }
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", model=" + model +
                ", yearProduction=" + yearProduction +
                ", typeOfFuel=" + typeOfFuel +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", Color='" + color + '\'' +
                '}';
    }
}
