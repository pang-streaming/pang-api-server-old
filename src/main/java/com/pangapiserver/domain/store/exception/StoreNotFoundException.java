package com.pangapiserver.domain.store.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StoreNotFoundException extends BasicException {
    public StoreNotFoundException() {
        super(StoreExceptionStatusCode.STORE_NOT_FOUND);
    }
}
