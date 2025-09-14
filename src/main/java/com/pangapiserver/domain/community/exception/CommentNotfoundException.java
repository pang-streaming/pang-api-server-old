package com.pangapiserver.domain.community.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class CommentNotfoundException extends BasicException {
    public CommentNotfoundException() {
        super(CommunityExceptionstatusCode.COMMENT_NOTFOUND_EXCEPTION);
    }
}
