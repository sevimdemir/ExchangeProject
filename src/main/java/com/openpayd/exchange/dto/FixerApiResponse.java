package com.openpayd.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixerApiResponse implements Serializable {

    private Boolean success;

    private Long timestamp;

    private String base;

    private Date date;

    private Map<String, BigDecimal> rates;
}
