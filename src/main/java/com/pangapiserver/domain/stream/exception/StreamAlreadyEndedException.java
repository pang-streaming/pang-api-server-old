package com.pangapiserver.domain.stream.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class StreamAlreadyEndedException extends BasicException {
    public StreamAlreadyEndedException() {
        super(StreamExceptionStatusCode.STREAM_ALREADY_ENDED);
    }
}
