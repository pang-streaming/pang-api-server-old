package com.pangapiserver.application.community.data;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddPostRequest (
    @NotBlank
    String title,

    @NotBlank
    String content,

    @NotNull
    Long communityId
) {
    public PostEntity toEntity(AddPostRequest request, UserEntity user, CommunityEntity community) {
        return PostEntity.builder()
            .title(request.title)
            .content(request.content)
            .user(user)
            .community(community)
            .build();
    }
}
