package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.Nested;

public class Model extends BaseEntity {
    String name;
    @Nested
    Brand brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
