package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserExceptionStatusCode implements StatusCode {
    USER_ALREADY_EXISTS(400, "이미 존재하는 유저입니다."),
    PASSWORD_INCORRECT(401, "잘못된 비밀번호입니다.")
    ;
    private final int status;
    private final String message;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
