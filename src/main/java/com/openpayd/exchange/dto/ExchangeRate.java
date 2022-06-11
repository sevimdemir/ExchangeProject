package com.openpayd.exchange.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate implements Serializable {

    private String currency;

    private BigDecimal rate;

    private Date operationDate;
}
