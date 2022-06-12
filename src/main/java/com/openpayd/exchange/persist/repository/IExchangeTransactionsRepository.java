package com.openpayd.exchange.persist.repository;

import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.dto.FixerApiResponse;
import com.openpayd.exchange.exception.BaseException;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IExchangeTransactionsRepository {

    ExchangeTransaction createExchangeTransaction(ExchangeTransaction exchangeTransaction) throws BaseException;

    ExchangeTransaction getExchangeTransactionByTransactionId(Long transacionId) throws BaseException;

    List<ExchangeTransaction> getExchangeTransactionsByTransactionDate(Date transacionDate, Pageable pageable) throws BaseException;
}
