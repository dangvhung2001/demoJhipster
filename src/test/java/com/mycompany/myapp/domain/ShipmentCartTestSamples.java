package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ShipmentCartTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ShipmentCart getShipmentCartSample1() {
        return new ShipmentCart()
            .id(1L)
            .firstName("firstName1")
            .lastName("lastName1")
            .street("street1")
            .postalCode("postalCode1")
            .city("city1")
            .country("country1")
            .phoneToTheReceiver("phoneToTheReceiver1")
            .firm("firm1")
            .taxNumber("taxNumber1");
    }

    public static ShipmentCart getShipmentCartSample2() {
        return new ShipmentCart()
            .id(2L)
            .firstName("firstName2")
            .lastName("lastName2")
            .street("street2")
            .postalCode("postalCode2")
            .city("city2")
            .country("country2")
            .phoneToTheReceiver("phoneToTheReceiver2")
            .firm("firm2")
            .taxNumber("taxNumber2");
    }

    public static ShipmentCart getShipmentCartRandomSampleGenerator() {
        return new ShipmentCart()
            .id(longCount.incrementAndGet())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .street(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .phoneToTheReceiver(UUID.randomUUID().toString())
            .firm(UUID.randomUUID().toString())
            .taxNumber(UUID.randomUUID().toString());
    }
}
