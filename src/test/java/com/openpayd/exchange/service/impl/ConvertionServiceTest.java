package com.openpayd.exchange.service.impl;

import com.github.javafaker.Faker;
import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.service.IExchangeRateService;
import com.openpayd.exchange.service.IExchangeTransactionService;
import com.openpayd.exchange.service.dummy.ExchangeRateDummy;
import com.openpayd.exchange.util.RateUtils;
import com.openpayd.exchange.util.mapper.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConvertionServiceTest {
    @Mock
    IExchangeRateService exchangeRateService;

    @Mock
    IExchangeTransactionService exchangeTransactionService;

    @Mock
    ObjectMapper objectMapper;

    ConvertionService convertionService;

    @BeforeEach
    void setUp() {
        convertionService = new ConvertionService(exchangeRateService, exchangeTransactionService, objectMapper);
    }

    @Test
    void convertionRate() {
        try {
            ConvertionRate convertionRate = ConvertionRate.builder()
                    .fromCurrency(Faker.instance().currency().code())
                    .toCurrency(Faker.instance().currency().code())
                    .build();
            when(exchangeRateService.getTodaysRateOf(Mockito.anyString())).thenReturn(ExchangeRateDummy.singleDummy());
            ConvertionRate convertionRateResponse = convertionService.convertionRate(convertionRate);
            assertEquals(0 ,BigDecimal.ONE.compareTo(convertionRateResponse.getConvertionRate()));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }

    @Test
    void convertionAmount() {
        try {
            ExchangeTransaction exchangeTransaction = ExchangeTransaction.builder()
                    .fromCurrency(Faker.instance().currency().code())
                    .toCurrency(Faker.instance().currency().code())
                    .amount(BigDecimal.TEN)
                    .build();
            when(exchangeRateService.getTodaysRateOf(Mockito.anyString())).thenReturn(ExchangeRateDummy.singleDummy());
            when(exchangeTransactionService.createExchangeTransaction(Mockito.any(ExchangeTransaction.class))).thenReturn(exchangeTransaction);

            ExchangeTransaction response = convertionService.convertionAmount(exchangeTransaction);

            assertEquals(0 ,BigDecimal.ONE.compareTo(response.getFromRate()));
            assertEquals(0 ,BigDecimal.ONE.compareTo(response.getToRate()));
            assertEquals(0 ,BigDecimal.TEN.compareTo(response.getConvertAmount()));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }
}