package com.pangapiserver.application.follow.data;

import com.pangapiserver.domain.user.entity.UserEntity;

public record FollowingResponse (
        String image,
        String nickname,
        Long follower
) {
    public static FollowingResponse toFollowing(UserEntity follower, Long followerCount) {
        return new FollowingResponse(
                follower.getProfileImage(),
                follower.getNickname(),
                followerCount
        );
    }

    public static FollowingResponse toFollower(UserEntity following, Long followerCount) {
        return new FollowingResponse(
                following.getProfileImage(),
                following.getNickname(),
                followerCount
        );
    }
}
