package com.pangapiserver.infrastructure.elasticsearch.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ElasticsearchConnectionException extends BasicException {
    public ElasticsearchConnectionException() {
        super(ElasticsearchExceptionStatusCode.ELASTICSEARCH_CONNECTION_FAILED);
    }
}
