package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.stream.entity.StreamType;

public record StreamStatusResponse(
        StreamStatus status,
        StreamType streamType
) {
    public static StreamStatusResponse of(StreamStatus status, StreamType type) {
        return new StreamStatusResponse(status, type);
    }
}
