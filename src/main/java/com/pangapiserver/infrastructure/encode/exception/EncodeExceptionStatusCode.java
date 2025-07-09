package com.pangapiserver.infrastructure.encode.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum EncodeExceptionStatusCode implements StatusCode {
    ENCODED_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "기본값 암호화에 실패했습니다")
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
