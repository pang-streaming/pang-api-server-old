package com.pangapiserver.infrastructure.elasticsearch.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ElasticsearchExceptionStatusCode implements StatusCode {
    ELASTICSEARCH_CONNECTION_FAILED(HttpStatus.BAD_REQUEST, "Elasticsearch 연결에 실패했습니다.")

    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
