package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrderMainTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OrderMain getOrderMainSample1() {
        return new OrderMain()
            .id(1L)
            .buyerLogin("buyerLogin1")
            .buyerFirstName("buyerFirstName1")
            .buyerLastName("buyerLastName1")
            .buyerEmail("buyerEmail1")
            .buyerPhone("buyerPhone1");
    }

    public static OrderMain getOrderMainSample2() {
        return new OrderMain()
            .id(2L)
            .buyerLogin("buyerLogin2")
            .buyerFirstName("buyerFirstName2")
            .buyerLastName("buyerLastName2")
            .buyerEmail("buyerEmail2")
            .buyerPhone("buyerPhone2");
    }

    public static OrderMain getOrderMainRandomSampleGenerator() {
        return new OrderMain()
            .id(longCount.incrementAndGet())
            .buyerLogin(UUID.randomUUID().toString())
            .buyerFirstName(UUID.randomUUID().toString())
            .buyerLastName(UUID.randomUUID().toString())
            .buyerEmail(UUID.randomUUID().toString())
            .buyerPhone(UUID.randomUUID().toString());
    }
}
