package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class InvalidTokenTypeException extends BasicException {
    public InvalidTokenTypeException() {
        super(UserExceptionStatusCode.INVALID_TOKEN_TYPE);
    }
}