package com.openpayd.exchange.persist.dao;

import com.openpayd.exchange.persist.entity.ExchangeTransactionsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IExchangeTransactionsDao extends JpaRepository<ExchangeTransactionsEntity, Long> {
    List<ExchangeTransactionsEntity> findByTransactionDateBetween(Date fromTransactionDate, Date toTransactionDate, Pageable page);
}
