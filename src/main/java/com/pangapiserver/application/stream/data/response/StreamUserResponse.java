package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamKeyEntity;

import java.time.LocalDateTime;

public record StreamUserResponse(
        String username,
        LocalDateTime startAt
) {
    public static StreamUserResponse of(StreamKeyEntity entity, StreamEntity stream) {
        return new StreamUserResponse(entity.getUser().getUsername(), stream.getStartAt());
    }
}
