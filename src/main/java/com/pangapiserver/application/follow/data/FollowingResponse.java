package com.pangapiserver.application.follow.data;

import com.pangapiserver.domain.user.entity.UserEntity;

public record FollowingResponse (
        String image,
        String nickname,
        Long follower
) {
    public static FollowingResponse of(UserEntity user, Long followerCount) {
        return new FollowingResponse(
                user.getProfileImage(),
                user.getNickname(),
                followerCount
        );
    }
}
