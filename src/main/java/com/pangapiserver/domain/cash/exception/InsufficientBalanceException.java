package com.pangapiserver.domain.cash.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class InsufficientBalanceException extends BasicException {
    public InsufficientBalanceException() {
        super(CashExceptionStatusCode.INSUFFICIENT_BALANCE);
    }
}
