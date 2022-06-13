package com.openpayd.exchange.service.dummy;

import com.github.javafaker.Faker;
import com.openpayd.exchange.dto.ExchangeRate;

import java.math.BigDecimal;

public class ExchangeRateDummy {

    public static ExchangeRate singleDummy() {
        return singleDummyWithId(1);
    }

    private static ExchangeRate singleDummyWithId(int id) {
        return ExchangeRate.builder()
                .rate(BigDecimal.ONE)
                .currency(Faker.instance().currency().code())
                .operationDate(Faker.instance().date().birthday())
                .build();
    }
}
