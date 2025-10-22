package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class UnauthorizedException extends BasicException {
    public UnauthorizedException() {
        super(UserExceptionStatusCode.UNAUTHORIZED);
    }
}
