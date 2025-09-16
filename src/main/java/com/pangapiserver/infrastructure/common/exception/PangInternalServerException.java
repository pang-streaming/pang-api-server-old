package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class PangInternalServerException extends BasicException {
    public PangInternalServerException() {
        super(GlobalStatusCode.INTERNAL_SERVER_ERROR);
    }
}
