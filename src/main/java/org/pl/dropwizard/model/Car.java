package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.pl.dropwizard.model.enums.TypeOfFuel;

public class Car {
    @ColumnName("id_car")
    private Long id;
    @Nested
    @ColumnName("model_id")
    private Model model;
    @ColumnName("year_production")
    private Integer yearProduction;
    @ColumnName("type_of_fuel")
    private TypeOfFuel typeOfFuel;
    @ColumnName("engine_capacity")
    private Integer engineCapacity; //cm3
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
