package com.pangapiserver.domain.search.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ElasticsearchUsersSearchException extends BasicException {
    public ElasticsearchUsersSearchException() {
        super(SearchExceptionStatusCode.SEARCH_USERS_SEARCH_FAILED);
    }
}
