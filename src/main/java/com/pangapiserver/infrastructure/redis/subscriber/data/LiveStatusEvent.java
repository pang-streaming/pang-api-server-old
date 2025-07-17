package com.pangapiserver.infrastructure.redis.subscriber.data;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

public record LiveStatusEvent(
    String streamerId,
    Boolean isStreaming
) {
    public StreamEntity toEntity(UserEntity user, String url) {
        return StreamEntity.builder()
            .user(user)
            .title(setTitle(user.getNickname()))
            .url(url)
            .build();
    }

    private String setTitle(String name) {
        return name.concat("님의 방송을 구경하러 오세요");
    }
}
