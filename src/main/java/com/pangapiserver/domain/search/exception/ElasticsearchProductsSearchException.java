package com.pangapiserver.domain.search.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ElasticsearchProductsSearchException extends BasicException {
    public ElasticsearchProductsSearchException() {
        super(SearchExceptionStatusCode.SEARCH_PRODUCTS_SEARCH_FAILED);
    }
}
