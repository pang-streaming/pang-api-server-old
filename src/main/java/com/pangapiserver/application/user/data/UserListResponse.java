package com.pangapiserver.application.user.data;

import com.pangapiserver.application.follow.data.FollowerCountResponse;
import com.pangapiserver.domain.user.document.UserDocument;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.enumeration.Role;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserListResponse(
    UUID id,
    String username,
    String nickname,
    String profileImage,
    String bannerImage,
    String description,
    Long follower,
    Role role
) {
    public static List<UserListResponse> of(List<UserEntity> users, List<FollowerCountResponse> followers) {
        Map<UUID, Long> followerCountMap = followers.stream()
            .collect(Collectors.toMap(
                FollowerCountResponse::id,
                FollowerCountResponse::count
            ));

        return users.stream()
            .map(user -> new UserListResponse(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getProfileImage(),
                user.getBannerImage(),
                user.getDescription(),
                followerCountMap.getOrDefault(user.getId(), 0L),
                user.getRole()
            ))
            .toList();
    }

    public static List<UserListResponse> ofDocument(List<UserDocument> users, List<FollowerCountResponse> followers) {
        Map<UUID, Long> followerCountMap = followers.stream()
                .collect(Collectors.toMap(
                        FollowerCountResponse::id,
                        FollowerCountResponse::count
                ));

        return users.stream()
                .map(user -> new UserListResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getNickname(),
                        user.getProfileImage(),
                        user.getBannerImage(),
                        user.getDescription(),
                        followerCountMap.getOrDefault(user.getId(), 0L),
                        user.getRole()
                ))
                .toList();
    }
}