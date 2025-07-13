package com.pangapiserver.infrastructure.payment.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PayappExceptionStatusCode implements StatusCode {
    PAYMENT_GATEWAY_ERROR(HttpStatus.BAD_REQUEST, "결제 대행사(PayApp) 처리 중 오류가 발생했습니다.");

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
