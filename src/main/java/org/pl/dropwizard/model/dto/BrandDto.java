package org.pl.dropwizard.model.dto;

public class BrandDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BrandDto brandDto = new BrandDto();

        public Builder id(Long id) {
            brandDto.id = id;
            return this;
        }

        public Builder name(String name) {
            brandDto.name = name;
            return this;
        }

        public BrandDto build() {
            return brandDto;
        }
    }
}