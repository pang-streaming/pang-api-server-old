package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddPostRequest (
    @NotBlank
    String title,

    @NotBlank
    String content,

    @NotNull
    Long communityId,

    List<String> images
) {
    public PostEntity toEntity(AddPostRequest request, UserEntity user, CommunityEntity community) {
        return PostEntity.builder()
            .title(request.title)
            .content(request.content)
            .user(user)
            .community(community)
            .images(request.images)
            .build();
    }
}
