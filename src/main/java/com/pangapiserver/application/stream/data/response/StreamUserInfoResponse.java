package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamType;

public record StreamUserInfoResponse(
        StreamType type,
        Boolean isLive
) {
    public static StreamUserInfoResponse of(StreamType type, Boolean isLive) {
        return new StreamUserInfoResponse(type, isLive);
    }
}
