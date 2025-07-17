package com.pangapiserver.domain.stream.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StreamNotFoundException extends BasicException {
    public StreamNotFoundException() {
        super(StreamExceptionStatusCode.STREAM_NOT_FOUND);
    }
}
