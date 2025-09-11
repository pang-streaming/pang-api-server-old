package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.community.service.PostService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    /** 커뮤니티별 게시글 목록 조회 */
    public DataResponse<Page<PostListResponse>> getPostsByCommunity(Long communityId, Pageable pageable) {
        CommunityEntity community = communityService.findById(communityId);
        UUID communityOwnerId = community.getUser().getId(); // 커뮤니티 스트리머 ID
        System.out.println(communityOwnerId + "1");
        Page<PostEntity> postsPage = postService.getPostsByCommunity(community, pageable);
        System.out.println(communityOwnerId + "2");
        List<PostEntity> posts = new ArrayList<>(postsPage.getContent());

        PostEntity ownerPost = posts.stream()
                .filter(p -> p.getUser().getId().equals(communityOwnerId))
                .findFirst()
                .orElse(null);

        List<PostEntity> otherPosts = posts.stream()
                .filter(p -> !p.equals(ownerPost))
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .toList();

        List<PostEntity> finalPosts = new ArrayList<>();
        if (ownerPost != null) {
            finalPosts.add(ownerPost);
        }
        finalPosts.addAll(otherPosts);

        List<PostListResponse> dtoList = finalPosts.stream()
                .map(PostListResponse::fromEntity)
                .collect(Collectors.toList());

        Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<PostListResponse> resultPage = new PageImpl<>(dtoList, newPageable, postsPage.getTotalElements());

        return DataResponse.ok("게시글 목록 조회 성공", resultPage);
    }
}
