package com.openpayd.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertionRate implements Serializable {

    private String fromCurrency;

    private String toCurrency;

    private BigDecimal convertionRate;
}
