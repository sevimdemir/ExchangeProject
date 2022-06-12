package com.openpayd.exchange.util.mapper;


import com.openpayd.exchange.dto.ExchangeRate;
import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.persist.entity.ExchangeRatesEntity;
import com.openpayd.exchange.persist.entity.ExchangeTransactionsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    ExchangeRate entityToDto(ExchangeRatesEntity exchangeRatesEntity);

    ExchangeTransactionsEntity dtoToEntity(ExchangeTransaction exchangeTransaction);

    ExchangeTransaction entityToDto(ExchangeTransactionsEntity exchangeTransactionsEntity);
}

