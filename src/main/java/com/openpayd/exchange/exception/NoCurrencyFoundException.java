package com.openpayd.exchange.exception;

public class NoCurrencyFoundException extends BaseException {

    public NoCurrencyFoundException(String currency) {
        super("No Rate found for currency ".concat(currency));
    }
}
