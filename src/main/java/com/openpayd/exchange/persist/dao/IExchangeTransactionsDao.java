package com.openpayd.exchange.persist.dao;

import com.openpayd.exchange.persist.entity.ExchangeTransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IExchangeTransactionsDao extends JpaRepository<ExchangeTransactionsEntity, Long> {

    List<ExchangeTransactionsEntity> findByTransactionDate(Date transactionDate);
}
