package com.openpayd.exchange.exception;

public class UnSupportedCurrencyException extends BaseException {

    public UnSupportedCurrencyException(String currency) {
        super(currency.concat(" currency does not supported"), 10004L);
    }
}
