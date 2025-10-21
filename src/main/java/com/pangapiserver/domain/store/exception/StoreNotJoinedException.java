package com.pangapiserver.domain.store.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StoreNotJoinedException extends BasicException {
    public StoreNotJoinedException() {
        super(StoreExceptionStatusCode.STORE_NOT_JOINED);
    }
}
