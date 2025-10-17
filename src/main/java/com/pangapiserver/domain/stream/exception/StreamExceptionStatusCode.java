package com.pangapiserver.domain.stream.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StreamExceptionStatusCode implements StatusCode {
    STREAM_KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "스트림키를 찾을 수 없습니다."),
    STREAM_NOT_FOUND(HttpStatus.NOT_FOUND, "방송을 찾을 수 없습니다."),
    STREAM_ALREADY_ENDED(HttpStatus.BAD_REQUEST, "이미 종료된 방송입니다.")
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
