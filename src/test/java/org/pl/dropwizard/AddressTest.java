package org.pl.dropwizard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.pl.dropwizard.model.Address;
import org.pl.dropwizard.resource.AddressService;

import java.util.Arrays;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class AddressTest extends ContainerBaseTest {
    private static AddressService addressService;

    @BeforeEach
    void setUp() {
//        addressService = new AddressService(new AddressRepo());
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestByAddress() {
        return Arrays.asList(
                dynamicTest("create address",
                        () -> {
                            Address givenAddress = new Address();
                            addressService.create(givenAddress);
//                            Optional<Address> actualAddress = addressService.findById(1L);
//                            Assert.assertEquals(givenAddress.getCity(), actualAddress.get().getCity());
                        })
        );
    }
}
