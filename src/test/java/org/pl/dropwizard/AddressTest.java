package org.pl.dropwizard;

import io.dropwizard.testing.junit5.DropwizardClientExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pl.dropwizard.dao.BrandDao;
import org.pl.dropwizard.dao.CarDao;
import org.pl.dropwizard.dao.ModelDao;
import org.pl.dropwizard.model.dto.BrandDto;
import org.pl.dropwizard.resource.BrandService;
import org.pl.dropwizard.resource.CarService;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AddressTest extends ContainerBaseTest {
    private static BrandService brandService;
    private static CarService carService;
    private static DropwizardClientExtension dropwizard;

    @BeforeEach
    void setUp() {
        brandService = new BrandService(jdbi.onDemand(BrandDao.class));
        carService = new CarService(jdbi.onDemand(CarDao.class), new ModelDao(jdbi));
        dropwizard = new DropwizardClientExtension(brandService);
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestByAddress() {
        return Arrays.asList(
                dynamicTest("create",
                        () -> {
                            BrandDto brandDto = BrandDto.builder()
                                    .name("Vectra")
                                    .build();
                            Response createBrand = brandService.create(brandDto);
                            Assertions.assertEquals(createBrand.getStatus(), 200);
                        })
//                ,
//                dynamicTest("resource",
//                        () -> {
//                    final URL url = new URL(dropwizard.baseUri());
//                            Assertions.assertEquals(createBrand.getStatus(), 200);
//                })
        );
    }
}
