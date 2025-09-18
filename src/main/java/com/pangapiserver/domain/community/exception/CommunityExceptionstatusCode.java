package com.pangapiserver.domain.community.exception;

import com.pangapiserver.domain.common.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommunityExceptionstatusCode implements StatusCode {
    POST_NOTFOUND_EXCEPTION(HttpStatus.NOT_FOUND, "없는 게시물입니다."),
    COMMUNITY_NOTFOUND_EXCEPTION(HttpStatus.NOT_FOUND, "없는 커뮤니티입니다."),
    COMMENT_NOTFOUND_EXCEPTION(HttpStatus.NOT_FOUND, "없는 댓글입니다."),
    IMAGE_UPLOAD_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지 업로드중 오류가 발생했습니다.")
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
