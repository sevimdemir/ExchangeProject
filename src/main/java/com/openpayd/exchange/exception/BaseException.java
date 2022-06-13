package com.openpayd.exchange.exception;

public class BaseException extends Exception {

    private final Long errorCode;

    public BaseException(String message, Long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Long getErrorCode() {
        return errorCode;
    }
}
