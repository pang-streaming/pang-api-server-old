package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.community.service.PostService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class PostUseCase {
    private final PostService postService;
    private final CommunityService communityService;
    private final UserAuthenticationHolder userAuthHolder;

    /** 게시글 추가 */
    public Response addPost(AddPostRequest request) {
        UserEntity user = userAuthHolder.current();
        CommunityEntity community = communityService.findById(request.communityId());
        PostEntity post = request.toEntity(request, user, community);
        postService.save(post);
        return Response.ok("post success");
    }

}
