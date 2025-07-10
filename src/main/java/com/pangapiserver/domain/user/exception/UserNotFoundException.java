package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException() {
        super(UserExceptionStatusCode.USER_NOT_FOUND);
    }
}
