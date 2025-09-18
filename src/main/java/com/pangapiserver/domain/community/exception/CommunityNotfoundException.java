package com.pangapiserver.domain.community.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class CommunityNotfoundException extends BasicException {
    public CommunityNotfoundException() {
        super(CommunityExceptionstatusCode.COMMUNITY_NOTFOUND_EXCEPTION);
    }
}
