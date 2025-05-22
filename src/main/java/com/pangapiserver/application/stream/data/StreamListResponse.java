package com.pangapiserver.application.stream.data;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;

public record StreamListResponse (
        String nickname,
        String profileImage,
        String badgeImage,
        String title,
        String streamImage,
        String streamUrl
) {
    public static StreamListResponse of(StreamEntity stream) {
        UserEntity user = stream.getUser();
        return new StreamListResponse(
                user.getNickname(),
                user.getProfileImage(),
                null,
                stream.getTitle(),
                null,
                stream.getUrl()
        );
    }
}