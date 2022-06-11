package com.openpayd.exchange.service;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.exception.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface IConvertionService {

    ConvertionRate convertionRate(ConvertionRate convertionRate) throws BaseException;
}
