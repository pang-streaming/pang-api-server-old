package com.pangapiserver.domain.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GlobalExceptionStatusCode implements StatusCode{
    INTERNAL_SERVER_ERROR(500, "서버 오류입니다."),
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
