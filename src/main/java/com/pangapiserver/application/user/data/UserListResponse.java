package com.pangapiserver.application.user.data;

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
    Long follower,
    Role role
) {
    public static List<UserListResponse> of(List<UserEntity> users, List<Object[]> followers) {
        Map<UUID, Long> followerCountMap = followers.stream()
            .collect(Collectors.toMap(
                follower -> (UUID) follower[0],
                follower -> (Long) follower[1]
            ));

        return users.stream()
            .map(user -> new UserListResponse(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getProfileImage(),
                user.getBannerImage(),
                followerCountMap.getOrDefault(user.getId(), 0L),
                user.getRole()
            ))
            .toList();
    }
}