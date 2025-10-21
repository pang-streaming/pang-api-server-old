package com.pangapiserver.domain.search.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ElasticsearchStreamsSearchException extends BasicException {
    public ElasticsearchStreamsSearchException() {
        super(SearchExceptionStatusCode.SEARCH_STREAMS_SEARCH_FAILED);
    }
}
