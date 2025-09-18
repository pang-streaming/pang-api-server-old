package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.PostEntity;

import java.time.LocalDateTime;

public record PostListResponse(
        long id,
        String title,
        int likes,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostListResponse fromEntity(PostEntity postEntity) {
        return new PostListResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getPostLikes().size(),
                postEntity.getUser().getNickname(),
                postEntity.getCreatedAt(),
                postEntity.getModifiedAt()
        );
    }
}
