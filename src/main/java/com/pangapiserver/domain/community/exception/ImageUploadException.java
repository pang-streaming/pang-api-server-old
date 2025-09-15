package com.pangapiserver.domain.community.exception;

import com.pangapiserver.domain.common.exception.BasicException;

public class ImageUploadException extends BasicException {
    public ImageUploadException() {
        super(CommunityExceptionstatusCode.IMAGE_UPLOAD_EXCEPTION);
    }
}
