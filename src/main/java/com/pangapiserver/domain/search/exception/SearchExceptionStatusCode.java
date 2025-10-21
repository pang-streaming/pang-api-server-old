package com.pangapiserver.domain.search.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SearchExceptionStatusCode implements StatusCode {
    SEARCH_STREAMS_SEARCH_FAILED(HttpStatus.BAD_REQUEST, "Elasticsearch 방송 검색에 실패했습니다."),
    SEARCH_USERS_SEARCH_FAILED(HttpStatus.BAD_REQUEST, "Elasticsearch 유저 검색에 실패했습니다."),
    SEARCH_PRODUCTS_SEARCH_FAILED(HttpStatus.BAD_REQUEST, "Elasticsearch 상품 검색에 실패했습니다."),
    DOCUMENT_REINDEXING_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Elasticsearch document reindexing 을 실패했습니다."),
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
