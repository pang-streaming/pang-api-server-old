package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.CommentEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentResponse(
        Long id,
        String content,
        CommentUserDto user,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<CommentResponse> children
) {
    public static CommentResponse fromEntity(CommentEntity comment, List<CommentResponse> children) {
        return CommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .user(CommentUserDto.fromEntity(comment.getUser()))
            .createdAt(comment.getCreatedAt())
            .modifiedAt(comment.getModifiedAt())
            .children(children)
            .build();
    }
}

