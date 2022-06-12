package com.openpayd.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateUtils {
    private static final int RATE_SCALE = 6;

    private static final int AMOUNT_SCALE = 2;

    public static BigDecimal toParity(BigDecimal fromRate, BigDecimal toRate) {
        return toRate.divide(fromRate, RATE_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal convertAmount(BigDecimal amount, BigDecimal fromRate, BigDecimal toRate) {
        return amount.multiply(toParity(fromRate, toRate)).setScale(AMOUNT_SCALE, RoundingMode.HALF_UP);
    }
}
