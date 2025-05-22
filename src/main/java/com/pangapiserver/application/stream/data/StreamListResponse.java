package com.pangapiserver.application.stream.data;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;

import java.util.List;
import java.util.stream.Collectors;

public record StreamListResponse (
        String nickname,
        String profileImage,
        String badgeImage,
        String title,
        String streamImage,
        String streamUrl
) {
    public static List<StreamListResponse> of(List<StreamEntity> items) {
        return items.stream()
                .map(StreamListResponse::toEntity)
                .collect(Collectors.toList());
    }

    private static StreamListResponse toEntity(StreamEntity stream) {
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