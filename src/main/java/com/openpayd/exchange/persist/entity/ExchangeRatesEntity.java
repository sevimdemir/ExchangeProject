package com.openpayd.exchange.persist.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "EXCHANGE_RATES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {@Index(name = "uniqueIndex", columnList = "currency, operation_date", unique = true) })
public class ExchangeRatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "operation_date", nullable = false)
    private Date operationDate;

}
