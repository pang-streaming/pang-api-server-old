package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class UserAleadyExistException extends BasicException {
    public UserAleadyExistException() {
        super(UserExceptionStatusCode.USER_ALREADY_EXISTS);
    }
}
