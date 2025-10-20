package com.pangapiserver.domain.search.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class DocumentReindexingException extends BasicException {
        public DocumentReindexingException () {
            super(SearchExceptionStatusCode.SEARCH_PRODUCTS_SEARCH_FAILED);
        }
    }

