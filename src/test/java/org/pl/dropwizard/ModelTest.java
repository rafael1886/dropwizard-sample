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

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static javax.ws.rs.core.Response.Status.*;
import static org.junit.jupiter.api.Assertions.*;
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
    Iterable<DynamicTest> dynamicTests() {
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

                            assertEquals(CREATED, createModel.getStatusInfo());
                            assertTrue(actualModelDto.getId() != null);
                            assertEquals(givenModelDto.getName(), actualModelDto.getName());
                            assertEquals(givenModelDto.getBrand(), actualModelDto.getBrand());
                        }),
                dynamicTest("update model",
                        () -> {
                            BrandDto givenBrandDto = BrandDto.builder()
                                    .name("Fiat")
                                    .build();
                            Response createBrand = brandService.create(givenBrandDto);
                            BrandDto actualBrand = (BrandDto) createBrand.getEntity();

                            ModelDto givenModelDto = ModelDto.builder()
                                    .name("Bravo")
                                    .brand(actualBrand.getId())
                                    .build();
                            Response createModel = modelService.create(givenModelDto);
                            ModelDto modelDto = (ModelDto) createModel.getEntity();
                            ModelDto createModelDto = (ModelDto) modelService.findById(modelDto.getId()).getEntity();

                            ModelDto tempModelDto = ModelDto.builder()
                                    .id(createModelDto.getId())
                                    .name("Tipo")
                                    .brand(createModelDto.getBrand())
                                    .build();
                            Response response = modelService.update(tempModelDto, tempModelDto.getId());
                            ModelDto findMdelUpdate = (ModelDto) modelService.findById(modelDto.getId()).getEntity();

                            assertEquals(OK.getStatusCode(), response.getStatus());
                            assertTrue(findMdelUpdate.getId() != null);
                            assertEquals(tempModelDto.getName(), findMdelUpdate.getName());
                            assertEquals(tempModelDto.getBrand(), findMdelUpdate.getBrand());
                        }),
                dynamicTest("delete model",
                        () -> {
                            BrandDto givenBrandDto = BrandDto.builder()
                                    .name("Honda")
                                    .build();
                            Response createBrand = brandService.create(givenBrandDto);
                            BrandDto actualBrand = (BrandDto) createBrand.getEntity();

                            ModelDto givenModelDto = ModelDto.builder()
                                    .name("Civic")
                                    .brand(actualBrand.getId())
                                    .build();
                            Response createModel = modelService.create(givenModelDto);
                            ModelDto modelDto = (ModelDto) createModel.getEntity();
                            ModelDto createModelDto = (ModelDto) modelService.findById(modelDto.getId()).getEntity();

                            Response response = modelService.delete(createModelDto.getId());
                            WebApplicationException actualWebApplicationException = assertThrows(WebApplicationException.class,
                                    () -> modelService.findById(modelDto.getId()));

                            assertEquals(NO_CONTENT, response.getStatusInfo());
                            assertEquals(NOT_FOUND, actualWebApplicationException.getResponse().getStatusInfo());
                            assertEquals("Model not found, id = " + modelDto.getId(), actualWebApplicationException.getMessage());
                        })
        );
    }
}