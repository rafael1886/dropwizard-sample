package org.pl.dropwizard.model;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
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
