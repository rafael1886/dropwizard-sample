package org.pl.dropwizard.model.dto;

public class ModelDto {
    private Long id;
    private String name;
    private Long brand;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "ModelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ModelDto modelDto = new ModelDto();

        public Builder id(Long id) {
            modelDto.id = id;
            return this;
        }

        public Builder name(String name) {
            modelDto.name = name;
            return this;
        }

        public Builder brand(Long brand) {
            modelDto.brand = brand;
            return this;
        }

        public ModelDto build() {
            return modelDto;
        }
    }
}