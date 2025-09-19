package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;

import java.time.LocalDateTime;

public record StreamUserResponse(
        String nickname,
        LocalDateTime createdAt
) {
    public static StreamUserResponse of(StreamKeyEntity entity) {
        return new StreamUserResponse(entity.getUser().getNickname(), entity.getCreatedAt());
    }
}
