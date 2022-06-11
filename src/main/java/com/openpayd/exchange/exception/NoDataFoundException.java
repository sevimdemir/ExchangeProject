package com.openpayd.exchange.exception;

public class NoDataFoundException extends BaseException {

    public NoDataFoundException(String currency) {
        super("No Rate found for currency ".concat(currency));
    }
}
