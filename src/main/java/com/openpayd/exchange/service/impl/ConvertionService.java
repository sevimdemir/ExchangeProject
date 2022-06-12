package com.openpayd.exchange.service.impl;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.service.IConvertionService;
import com.openpayd.exchange.service.IExchangeRateService;
import com.openpayd.exchange.service.IExchangeTransactionService;
import com.openpayd.exchange.util.RateUtils;
import com.openpayd.exchange.util.mapper.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConvertionService implements IConvertionService {

    private final IExchangeRateService exchangeRateService;

    private final IExchangeTransactionService exchangeTransactionService;

    private final ObjectMapper objectMapper;

    @Override
    public ConvertionRate convertionRate(ConvertionRate convertionRate) throws BaseException {
        ExchangeRate fromRate = exchangeRateService.getTodaysRateOf(convertionRate.getFromCurrency());
        ExchangeRate toRate = exchangeRateService.getTodaysRateOf(convertionRate.getToCurrency());
        BigDecimal responseRate = RateUtils.toParity(fromRate.getRate(), toRate.getRate());
        convertionRate.setConvertionRate(responseRate);
        return convertionRate;
    }

    @Override
    public ExchangeTransaction convertionAmount(ExchangeTransaction exchangeTransaction) throws BaseException {
        ExchangeRate fromRate = exchangeRateService.getTodaysRateOf(exchangeTransaction.getFromCurrency());
        ExchangeRate toRate = exchangeRateService.getTodaysRateOf(exchangeTransaction.getToCurrency());
        exchangeTransaction.setFromRate(fromRate.getRate());
        exchangeTransaction.setToRate(toRate.getRate());
        exchangeTransaction.setConvertAmount(RateUtils.convertAmount(exchangeTransaction.getAmount(), fromRate.getRate(), toRate.getRate()));
        return createExchangeTransaction(exchangeTransaction);
    }

    private ExchangeTransaction createExchangeTransaction(ExchangeTransaction exchangeTransaction) throws BaseException {
        return exchangeTransactionService.createExchangeTransaction(exchangeTransaction);
    }
}
