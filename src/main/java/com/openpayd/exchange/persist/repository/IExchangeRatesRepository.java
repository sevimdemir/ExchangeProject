package com.openpayd.exchange.persist.repository;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.FixerApiResponse;
import com.openpayd.exchange.exception.BaseException;

public interface IExchangeRatesRepository {

    ExchangeRate getTodaysRateOf(String currency) throws BaseException;

    void createExchangeRate(FixerApiResponse fixerApiResponse) throws BaseException;
}
