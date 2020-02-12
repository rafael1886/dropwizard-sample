package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.pl.dropwizard.model.enums.TypeOfFuel;

public class Car extends BaseEntity {
    @Nested
    private Model model;
    @ColumnName("year_production")
    private Integer yearProduction;
    @ColumnName("type_of_fuel")
    private TypeOfFuel typeOfFuel;
    @ColumnName("engine_capacity")
    private Integer engineCapacity; //cm3
    private String Color;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(Integer yearProduction) {
        this.yearProduction = yearProduction;
    }

    public TypeOfFuel getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(TypeOfFuel typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Car car = new Car();

        public Builder id(Long id) {
            car.setId(id);
            return this;
        }

        public Builder model(Model model) {
            car.model = model;
            return this;
        }

        public Builder yearProduction(Integer yearProduction) {
            car.yearProduction = yearProduction;
            return this;
        }

        public Builder typeOfFuel(TypeOfFuel typeOfFuel) {
            car.typeOfFuel = typeOfFuel;
            return this;
        }

        public Builder engineCapacity(Integer engineCapacity) {
            car.engineCapacity = engineCapacity;
            return this;
        }

        public Builder color(String Color) {
            car.Color = Color;
            return this;
        }

        public Car build() {
            return car;
        }
    }
}
