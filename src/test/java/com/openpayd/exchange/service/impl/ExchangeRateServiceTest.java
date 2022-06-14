package com.openpayd.exchange.service.impl;

import com.github.javafaker.Faker;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.FixerApiResponse;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.exception.NoCurrencyFoundException;
import com.openpayd.exchange.persist.repository.IExchangeRatesRepository;
import com.openpayd.exchange.service.dummy.ExchangeRateDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    IExchangeRatesRepository exchangeRatesRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    Environment environment;

    ExchangeRateService exchangeRateService;

    @BeforeEach
    void setUp() {
        exchangeRateService = new ExchangeRateService(exchangeRatesRepository, restTemplate, environment);
    }

    @Test
    void getTodaysRateOf() {
        try {
            when(exchangeRatesRepository.getTodaysRateOf(Mockito.anyString())).thenReturn(ExchangeRateDummy.singleDummy());
            ExchangeRate todaysRateOf = exchangeRateService.getTodaysRateOf(Faker.instance().currency().code());
            assertEquals(0, BigDecimal.ONE.compareTo(todaysRateOf.getRate()));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }

    @Test
    void getTodaysRateOfWithException() {
        try {
            ResponseEntity<FixerApiResponse> httpResponse = Mockito.mock(ResponseEntity.class);
            when(httpResponse.getBody()).thenReturn(new FixerApiResponse());
            when(exchangeRatesRepository.getTodaysRateOf(Mockito.anyString())).thenThrow(new NoCurrencyFoundException(Faker.instance().currency().code()));
            when(environment.getProperty("api.exchange.key")).thenReturn(Faker.instance().lorem().word());
            when(environment.getProperty("api.exchange.uri")).thenReturn("https://api.apilayer.com/fixer/latest");
            when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class), Mockito.any(Map.class))).thenReturn(httpResponse);
            ExchangeRate todaysRateOf = exchangeRateService.getTodaysRateOf(Faker.instance().currency().code());
            verify(restTemplate).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class), Mockito.any(Map.class));
        } catch (BaseException e) {
            assertEquals(e.getClass(), NoCurrencyFoundException.class);
        }
    }
}