package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {
    private long id;
    private String title;
    private String content;
    private int likes;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseDto fromEntity(PostEntity postEntity) {
        return PostResponseDto.builder()
            .id(postEntity.getId())
            .title(postEntity.getTitle())
            .content(postEntity.getContent())
            .likes(postEntity.getLikes())
            .nickname(postEntity.getUser().getNickname())
            .createdAt(postEntity.getCreatedAt())
            .updatedAt(postEntity.getModifiedAt())
            .build();
    }
}
