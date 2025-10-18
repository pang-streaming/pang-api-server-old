package com.pangapiserver.domain.store.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StoreExceptionStatusCode implements StatusCode {
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "스토어를 찾을 수 없습니다."),
    STORE_ALREADY_JOINED(HttpStatus.BAD_REQUEST, "이미 가입된 스토어입니다.")
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
