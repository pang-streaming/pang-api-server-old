package com.pangapiserver.domain.user.exception;

import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.domain.common.exception.StatusCode;

public class UserPasswordIncorrectException extends BasicException {
    public UserPasswordIncorrectException() {
        super(UserExceptionStatusCode.PASSWORD_INCORRECT);
    }
}
