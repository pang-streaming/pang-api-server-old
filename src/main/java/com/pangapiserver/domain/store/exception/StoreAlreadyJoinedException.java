package com.pangapiserver.domain.store.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StoreAlreadyJoinedException extends BasicException {
    public StoreAlreadyJoinedException() {
        super(StoreExceptionStatusCode.STORE_ALREADY_JOINED);
    }
}
