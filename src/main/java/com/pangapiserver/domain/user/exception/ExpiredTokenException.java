package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ExpiredTokenException extends BasicException {
    public ExpiredTokenException() {
        super(UserExceptionStatusCode.EXPIRED_TOKEN);
    }
}