package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.stream.entity.StreamType;

public record StreamStatusResponse(
        String title,
        Long categoryId,
        StreamStatus status,
        StreamType streamType
) {
    public static StreamStatusResponse of(String title, Long categoryId, StreamStatus status, StreamType type) {
        return new StreamStatusResponse(title, categoryId, status, type);
    }
}
