package com.openpayd.exchange.persist.dao;

import com.openpayd.exchange.persist.entity.ExchangeRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface IExchangeRatesDao extends JpaRepository<ExchangeRatesEntity, Long> {
    Optional<ExchangeRatesEntity> findByCurrencyAndOperationDate(String currency, Date operationDate);
}
