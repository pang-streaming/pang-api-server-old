package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.user.entity.UserEntity;

public record CommentUserDto(
    String nickname,
    String profileImageUrl
) {
    public static CommentUserDto fromEntity(UserEntity user) {
        return new CommentUserDto(user.getNickname(), user.getProfileImage());
    }
}
