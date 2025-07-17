package com.pangapiserver.domain.stream.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StreamKeyNotFoundException extends BasicException {
    public StreamKeyNotFoundException() {
        super(StreamExceptionStatusCode.STREAM_KEY_NOT_FOUND);
    }
}
