package com.openpayd.exchange.exception;

import java.util.Date;

public class NoTransactionFoundException extends BaseException {

    public NoTransactionFoundException(Long id) {
        super("No Transaction found for id ".concat(id.toString()), 10002L);
    }

    public NoTransactionFoundException(Date transaction) {
        super("No Transaction found for date ".concat(transaction.toString()), 10002L);
    }
}
