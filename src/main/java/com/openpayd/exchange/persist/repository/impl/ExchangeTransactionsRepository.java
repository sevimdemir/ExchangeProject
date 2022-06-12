package com.openpayd.exchange.persist.repository.impl;

import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.exception.NoCurrencyFoundException;
import com.openpayd.exchange.exception.NoTransactionFoundException;
import com.openpayd.exchange.persist.dao.IExchangeTransactionsDao;
import com.openpayd.exchange.persist.entity.ExchangeTransactionsEntity;
import com.openpayd.exchange.persist.repository.IExchangeTransactionsRepository;
import com.openpayd.exchange.util.DateUtils;
import com.openpayd.exchange.util.mapper.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExchangeTransactionsRepository implements IExchangeTransactionsRepository {

    private final IExchangeTransactionsDao exchangeTransactionsDao;

    private final ObjectMapper objectMapper;

    @Override
    public ExchangeTransaction createExchangeTransaction(ExchangeTransaction exchangeTransaction) throws BaseException {
        exchangeTransaction.setTransactionDate(Calendar.getInstance().getTime());
        ExchangeTransactionsEntity save = exchangeTransactionsDao.save(objectMapper.dtoToEntity(exchangeTransaction));
        return objectMapper.entityToDto(save);
    }

    @Override
    public ExchangeTransaction getExchangeTransactionByTransactionId(Long transacionId) throws BaseException {
        Optional<ExchangeTransactionsEntity> exchangeRate = exchangeTransactionsDao.findById(transacionId);
        ExchangeTransactionsEntity exchangeTransactionsEntity = exchangeRate.orElseThrow(() -> new NoTransactionFoundException(transacionId));
        return objectMapper.entityToDto(exchangeTransactionsEntity);
    }

    @Override
    public List<ExchangeTransaction> getExchangeTransactionsByTransactionDate(Date transacionDate, Pageable pageable) throws BaseException {
        Date fromDate = DateUtils.getTruncDate(transacionDate);
        Date toDate = DateUtils.addDate(fromDate, 1);
        List<ExchangeTransactionsEntity> exchangeTransaction = exchangeTransactionsDao.findByTransactionDateBetween(fromDate, toDate, pageable);
        if (exchangeTransaction.isEmpty()) {
            throw new NoTransactionFoundException(transacionDate);
        }
        return exchangeTransaction.stream().map(entity -> objectMapper.entityToDto(entity)).collect(Collectors.toList());
    }
}
