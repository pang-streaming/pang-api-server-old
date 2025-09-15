package com.pangapiserver.domain.cash.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class PaymentGatewayException extends BasicException {
    public PaymentGatewayException() {
        super(CashExceptionStatusCode.PAYMENT_GATEWAY_EXCEPTION);
    }
}
