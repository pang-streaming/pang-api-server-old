package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class UserAlreadyExistException extends BasicException {
    public UserAlreadyExistException() {
        super(UserExceptionStatusCode.USER_ALREADY_EXISTS);
    }
}
