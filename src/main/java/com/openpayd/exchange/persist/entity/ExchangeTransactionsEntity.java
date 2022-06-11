package com.openpayd.exchange.persist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "EXCHANGE_TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {@Index(name = "transactionIndex", columnList = "transaction_date DESC") })
public class ExchangeTransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "from_rate", nullable = false)
    private BigDecimal fromRate;

    @Column(name = "to_rate", nullable = false)
    private BigDecimal toRate;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

}
