package org.pl.dropwizard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.pl.dropwizard.dao.BrandDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.dto.BrandDto;
import org.pl.dropwizard.model.dto.ModelDto;
import org.pl.dropwizard.resource.BrandService;
import org.pl.dropwizard.resource.ModelService;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ModelTest extends ContainerBaseTest {
    private static BrandService brandService;
    private static ModelService modelService;

    @BeforeEach
    void setUp() {
        final BrandDao brandDao = jdbi.onDemand(BrandDao.class);
        brandService = new BrandService(brandDao);
        modelService = new ModelService(new ModelDao(jdbi), brandDao);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestByCreate() {
        return Arrays.asList(
                dynamicTest("create brand",
                        () -> {
                            BrandDto givenBrandDto = BrandDto.builder()
                                    .name("Lexus")
                                    .build();
                            Response createBrand = brandService.create(givenBrandDto);
                            BrandDto actualBrand = (BrandDto) createBrand.getEntity();
                            assertEquals(createBrand.getStatus(), 201);
                            assertTrue(actualBrand.getId() != null);
                            assertEquals(actualBrand.getName(), givenBrandDto.getName());
                        }),
                dynamicTest("create model",
                        () -> {
                            BrandDto givenBrandDto = BrandDto.builder()
                                    .name("Mercedes-Benz")
                                    .build();
                            Response createBrand = brandService.create(givenBrandDto);
                            BrandDto actualBrand = (BrandDto) createBrand.getEntity();

                            ModelDto givenModelDto = ModelDto.builder()
                                    .name("AMG GT R")
                                    .brand(actualBrand.getId())
                                    .build();
                            Response createModel = modelService.create(givenModelDto);
                            ModelDto modelDto = (ModelDto) createModel.getEntity();
                            ModelDto actualModelDto = (ModelDto) modelService.findById(modelDto.getId()).getEntity();

                            assertEquals(createModel.getStatus(), 201);
                            assertTrue(actualModelDto.getId() != null);
                            assertEquals(actualModelDto.getName(), givenModelDto.getName());
                            assertEquals(actualModelDto.getBrand(), givenModelDto.getBrand());
                        })
        );
    }
}
