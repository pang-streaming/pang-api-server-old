package com.pangapiserver.application.user.data;

import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.Builder;

@Builder
public record UserDetailResponse (
        String nickname,
        String profileImage,
        String bannerImage,
        String description,
        Long communityId,
        Long followerCount,
        boolean isFollowed
) {
    public static UserDetailResponse of(UserEntity user, Long communityId, Long followerCount, boolean isFollowed) {
        return new UserDetailResponse(
                user.getNickname(),
                user.getProfileImage(),
                user.getBannerImage(),
                user.getDescription(),
                communityId,
                followerCount,
                isFollowed
        );
    }
}
