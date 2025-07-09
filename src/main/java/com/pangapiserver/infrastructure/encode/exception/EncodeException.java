package com.pangapiserver.infrastructure.encode.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class EncodeException extends BasicException {
    public EncodeException() {
        super(EncodeExceptionStatusCode.ENCODED_FAILED);
    }
}
