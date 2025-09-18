package com.pangapiserver.domain.community.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class PostNotFoundException extends BasicException {
    public PostNotFoundException() {
        super(CommunityExceptionstatusCode.POST_NOTFOUND_EXCEPTION);
    }
}
