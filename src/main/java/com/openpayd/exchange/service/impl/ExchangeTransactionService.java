package com.openpayd.exchange.service.impl;

import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.persist.repository.IExchangeTransactionsRepository;
import com.openpayd.exchange.service.IExchangeTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeTransactionService implements IExchangeTransactionService {

    private final IExchangeTransactionsRepository exchangeTransactionsRepository;

    @Override
    public ExchangeTransaction createExchangeTransaction(ExchangeTransaction exchangeTransaction) throws BaseException {
        return exchangeTransactionsRepository.createExchangeTransaction(exchangeTransaction);
    }

    @Override
    public List<ExchangeTransaction> findAllByTransactionDate(Date transactionDate, Pageable pageable) throws BaseException {
        return exchangeTransactionsRepository.getExchangeTransactionsByTransactionDate(transactionDate, pageable);
    }

    @Override
    public ExchangeTransaction findByTransactionId(Long transactionId) throws BaseException {
        return exchangeTransactionsRepository.getExchangeTransactionByTransactionId(transactionId);
    }
}
