package com.openpayd.exchange.service.impl;

import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.FixerApiResponse;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.exception.NoCurrencyFoundException;
import com.openpayd.exchange.exception.RemoteServiceException;
import com.openpayd.exchange.persist.repository.IExchangeRatesRepository;
import com.openpayd.exchange.service.IExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ExchangeRateService implements IExchangeRateService {

    private final IExchangeRatesRepository exchangeRatesRepository;

    private final RestTemplate restTemplate;

    private final Environment environment;

    @Override
    @Cacheable(value = "exchangeRatesCache")
    public ExchangeRate getTodaysRateOf(String currency) throws BaseException {
        ExchangeRate todaysRateOf = null;
        try {
            todaysRateOf = exchangeRatesRepository.getTodaysRateOf(currency);
            return todaysRateOf;
        } catch (NoCurrencyFoundException baseException) {
            createExchangeRates();
        }
        todaysRateOf = exchangeRatesRepository.getTodaysRateOf(currency);
        return todaysRateOf;
    }

    private FixerApiResponse getExchangeRates() throws BaseException {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set("apikey", environment.getProperty("api.exchange.key"));
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(environment.getProperty("api.exchange.uri"))
                .queryParam("base", "{base}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>() {{
            put("base", environment.getProperty("api.exchange.currency"));
        }};

        HttpEntity<FixerApiResponse> httpResponse = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                FixerApiResponse.class,
                params
        );

        if (httpResponse.getBody() == null) {
            throw new RemoteServiceException();
        }
        return httpResponse.getBody();
    }

    private void createExchangeRates() throws BaseException {
        FixerApiResponse rates = getExchangeRates();
        exchangeRatesRepository.createExchangeRate(rates);
    }
}
