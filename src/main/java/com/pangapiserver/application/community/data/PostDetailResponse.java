package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse(
        long id,
        String title,
        String content,
        int likes,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean liked,
        List<String> images
) {
        public static PostDetailResponse fromEntity(PostEntity postEntity, boolean liked) {
        return new PostDetailResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getPostLikes().size(),
                postEntity.getUser().getNickname(),
                postEntity.getCreatedAt(),
                postEntity.getModifiedAt(),
                liked,
                postEntity.getImages()
        );
    }
}
