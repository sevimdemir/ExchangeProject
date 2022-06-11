package com.openpayd.exchange.service.impl;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.persist.repository.IExchangeRatesRepository;
import com.openpayd.exchange.service.IConvertionService;
import com.openpayd.exchange.service.IExchangeRateService;
import com.openpayd.exchange.util.RateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ConvertionService implements IConvertionService {

    private final IExchangeRateService exchangeRateService;

    @Override
    public ConvertionRate convertionRate(ConvertionRate convertionRate) throws BaseException{
        ExchangeRate fromRate = exchangeRateService.getTodaysRateOf(convertionRate.getFromCurrency());
        ExchangeRate toRate = exchangeRateService.getTodaysRateOf(convertionRate.getToCurrency());
        BigDecimal responseRate = RateUtils.toParity(fromRate.getRate(), toRate.getRate());
        convertionRate.setConvertionRate(responseRate);
        return convertionRate;
    }
}
