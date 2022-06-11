package com.openpayd.exchange.persist.repository.impl;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.FixerApiResponse;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.exception.NoDataFoundException;
import com.openpayd.exchange.persist.dao.IExchangeRatesDao;
import com.openpayd.exchange.persist.entity.ExchangeRatesEntity;
import com.openpayd.exchange.persist.repository.IExchangeRatesRepository;
import com.openpayd.exchange.util.DateUtils;
import com.openpayd.exchange.util.mapper.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExchangeRatesRepository implements IExchangeRatesRepository {

    private final IExchangeRatesDao exchangeRatesDao;

    private final ObjectMapper mapper;

    @Override
    public ExchangeRate getTodaysRateOf(String currency) throws BaseException {
        Optional<ExchangeRatesEntity> exchangeRate = exchangeRatesDao.findByCurrencyAndOperationDate(currency, DateUtils.getTodayDate());
        if (exchangeRate.isEmpty()) {
            throw new NoDataFoundException(currency);
        }
        return mapper.entityToDto(exchangeRate.get());
    }

    @Override
    public void createExchangeRate(FixerApiResponse fixerApiResponse) throws BaseException {
        final Map<String, BigDecimal> rates = fixerApiResponse.getRates();
        List<ExchangeRatesEntity> collect = rates.keySet().stream()
                .map(key -> ExchangeRatesEntity.builder()
                        .rate(rates.get(key))
                        .operationDate(DateUtils.getTodayDate())
                        .currency(key)
                        .build()).collect(Collectors.toList());
        exchangeRatesDao.saveAll(collect);
    }
}
