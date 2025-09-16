package com.pangapiserver.domain.cash.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum CashExceptionStatusCode implements StatusCode {
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    PAYMENT_GATEWAY_EXCEPTION(HttpStatus.BAD_REQUEST, "PG사 예외 발생"),
    ;
    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
