package com.pangapiserver.domain.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BasicException extends RuntimeException {
    private final StatusCode statusCode;

    @Override
    public String getMessage() {
        return statusCode.getMessage();
    }
}
