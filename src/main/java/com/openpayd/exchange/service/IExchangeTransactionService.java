package com.openpayd.exchange.service;

import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IExchangeTransactionService {

    ExchangeTransaction createExchangeTransaction(ExchangeTransaction exchangeTransaction) throws BaseException;

    List<ExchangeTransaction> findAllByTransactionDate(Date transactionDate, Pageable pageable) throws BaseException;

    ExchangeTransaction findByTransactionId(Long transactionId) throws BaseException;
}
