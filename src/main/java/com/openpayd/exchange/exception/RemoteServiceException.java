package com.openpayd.exchange.exception;

public class RemoteServiceException extends BaseException {

    public RemoteServiceException() {
        super("Api service call failed");
    }
}
