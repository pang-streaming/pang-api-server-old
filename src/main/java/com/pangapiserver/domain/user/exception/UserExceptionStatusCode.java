package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserExceptionStatusCode implements StatusCode {
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다."),
    PASSWORD_INCORRECT(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다."),
    INVALID_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 타입입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다.")
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
