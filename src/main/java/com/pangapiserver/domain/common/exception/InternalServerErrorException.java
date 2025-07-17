package com.pangapiserver.domain.common.exception;

public class InternalServerErrorException extends BasicException {
    public InternalServerErrorException() {
        super(GlobalExceptionStatusCode.INTERNAL_SERVER_ERROR);
    }
}
