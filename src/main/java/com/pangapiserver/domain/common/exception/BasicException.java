package com.pangapiserver.domain.common.exception;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {
    private final StatusCode statusCode;
    private final String customMessage;

    public BasicException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
        this.customMessage = null;
    }

    public BasicException(StatusCode statusCode, String customMessage) {
        super(customMessage);
        this.statusCode = statusCode;
        this.customMessage = customMessage;
    }

    @Override
    public String getMessage() {
        return customMessage != null ? customMessage : statusCode.getMessage();
    }
}
