package com.pangapiserver.infrastructure.payment.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class PayappException extends BasicException {
    public PayappException(String message) {
        super(PayappExceptionStatusCode.PAYMENT_GATEWAY_ERROR, message);
    }
}