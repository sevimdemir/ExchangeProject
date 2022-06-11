package com.openpayd.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeTransaction implements Serializable {

    private String fromCurrency;

    private String toCurrency;

    private BigDecimal fromRate;

    private BigDecimal toRate;

    private Date transactionDate;
}
