package com.openpayd.exchange.service;

import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.exception.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface IExchangeRateService {

    ExchangeRate getTodaysRateOf(String currency) throws BaseException;
}
