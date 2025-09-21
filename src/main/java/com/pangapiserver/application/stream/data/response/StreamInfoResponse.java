package com.pangapiserver.application.stream.data.response;

import com.pangapiserver.domain.stream.entity.StreamEntity;

import java.util.UUID;

public record StreamInfoResponse(
        UUID streamId,
        String title,
        String url,
        UUID userId,
        String username,
        String nickname,
        String profileImage,
        int followers,
        boolean isFollowed
) {
    public static StreamInfoResponse of(StreamEntity stream, int followers, boolean isFollowed) {
        return new StreamInfoResponse(
            stream.getId(),
            stream.getTitle(),
            stream.getUrl(),
            stream.getUser().getId(),
            stream.getUser().getUsername(),
            stream.getUser().getNickname(),
            stream.getUser().getProfileImage(),
            followers,
            isFollowed
        );
    }
}