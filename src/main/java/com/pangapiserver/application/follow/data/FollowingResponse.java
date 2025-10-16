package com.pangapiserver.application.follow.data;

import com.pangapiserver.domain.user.entity.UserEntity;

public record FollowingResponse (
        String image,
        String username,
        String nickname,
        Long follower
) {
    public static FollowingResponse of(UserEntity user, Long followerCount) {
        return new FollowingResponse(
                user.getProfileImage(),
                user.getUsername(),
                user.getNickname(),
                followerCount
        );
    }
}
