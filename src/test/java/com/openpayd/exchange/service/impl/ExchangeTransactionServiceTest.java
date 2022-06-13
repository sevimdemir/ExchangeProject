package com.openpayd.exchange.service.impl;

import com.github.javafaker.Faker;
import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.persist.repository.IExchangeTransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeTransactionServiceTest {

    @Mock
    IExchangeTransactionsRepository exchangeTransactionsRepository;

    ExchangeTransactionService exchangeTransactionService;

    @BeforeEach
    void setUp() {
        exchangeTransactionService = new ExchangeTransactionService(exchangeTransactionsRepository);
    }

    @Test
    void createExchangeTransaction() {
        try {
            when(exchangeTransactionsRepository.createExchangeTransaction(Mockito.any(ExchangeTransaction.class))).thenReturn(null);
            exchangeTransactionService.createExchangeTransaction(new ExchangeTransaction());
            verify(exchangeTransactionsRepository, times(1)).createExchangeTransaction(Mockito.any(ExchangeTransaction.class));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }

    @Test
    void findAllByTransactionDate() {
        try {
            when(exchangeTransactionsRepository.getExchangeTransactionsByTransactionDate(Mockito.any(Date.class), Mockito.any(Pageable.class)))
                    .thenReturn(new ArrayList<ExchangeTransaction>());
            exchangeTransactionService.findAllByTransactionDate(Faker.instance().date().birthday(), Pageable.ofSize(1));
            verify(exchangeTransactionsRepository, times(1))
                    .getExchangeTransactionsByTransactionDate(Mockito.any(Date.class), Mockito.any(Pageable.class));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }

    @Test
    void findByTransactionId() {
        try {
            when(exchangeTransactionsRepository.getExchangeTransactionByTransactionId(Mockito.any(Long.class)))
                    .thenReturn(new ExchangeTransaction());
            exchangeTransactionService.findByTransactionId(Faker.instance().random().nextLong());
            verify(exchangeTransactionsRepository, times(1))
                    .getExchangeTransactionByTransactionId(Mockito.any(Long.class));
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }
}