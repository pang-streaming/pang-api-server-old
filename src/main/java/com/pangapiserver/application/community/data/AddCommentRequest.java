package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.CommentEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

public record AddCommentRequest(
    Long postId,
    String content,
    Long parentId
) {
    public CommentEntity toEntity(UserEntity user, CommentEntity parent) {
        return CommentEntity.builder()
            .content(content())
            .user(user)
            .pkPostId(postId())
            .pkMentionId(parent != null ? parent.getUser().getId() : null)
            .parent(parent)
            .build();
    }
}
