package com.openpayd.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateUtils {
    private static final int SCALE = 6;

    public static BigDecimal toParity(BigDecimal fromRate, BigDecimal toRate){
        return toRate.divide(fromRate, SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal convertAmount(BigDecimal amount, BigDecimal fromRate, BigDecimal toRate){
        return amount.multiply(toParity(fromRate, toRate));
    }
}
