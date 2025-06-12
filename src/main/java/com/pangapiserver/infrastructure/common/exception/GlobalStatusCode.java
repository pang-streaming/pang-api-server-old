package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum GlobalStatusCode implements StatusCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Pang Internal Server Error");
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
