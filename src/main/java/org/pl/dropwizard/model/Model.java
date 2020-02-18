package org.pl.dropwizard.model;

import org.jdbi.v3.core.mapper.Nested;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Model {//} extends BaseEntity {
    @ColumnName("id_model")
    private Long id;
    @ColumnName("name_model")
    private String name;
    @Nested
    @ColumnName("brand_id")
    private Brand brand;

    public Model() {
    }

    public Model(Long id, String name, Brand brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nested("m")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", brand=" + brand +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Model model = new Model();

        public Builder id(Long id) {
            model.setId(id);
            return this;
        }

        public Builder name(String name) {
            model.name = name;
            return this;
        }

        public Builder brand(Brand brand) {
            model.brand = brand;
            return this;
        }

        public Model build() {
            return model;
        }
    }
}
