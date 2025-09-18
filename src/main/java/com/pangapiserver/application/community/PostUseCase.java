package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.PostDetailResponse;
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.exception.ImageUploadException;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.community.service.PostService;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.s3.service.S3Service;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class PostUseCase {
    private final PostService postService;
    private final CommunityService communityService;
    private final UserAuthenticationHolder userAuthHolder;
    private final S3Service s3Service;

    /** 게시글 추가 */
    public Response addPost(AddPostRequest request) {
        UserEntity user = userAuthHolder.current();
        CommunityEntity community = communityService.findById(request.communityId());
        PostEntity post = request.toEntity(request, user, community);
        postService.save(post);
        return Response.ok("post success");
    }

    /** 커뮤니티별 게시글 목록 조회 */
    public DataResponse<Page<PostListResponse>> getPostsByCommunity(Long communityId, Pageable pageable, PostFilterType filter) {
        UserEntity user = userAuthHolder.current();
        Page<PostEntity> postsPage = postService.getPostsByCommunity(user, communityId, pageable, filter);

        List<PostListResponse> dtoList = postsPage.getContent().stream()
            .map(PostListResponse::fromEntity)
            .collect(Collectors.toList());

        Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<PostListResponse> resultPage = new PageImpl<>(dtoList, newPageable, postsPage.getTotalElements());

        return DataResponse.ok("게시글 목록 조회 성공", resultPage);
    }

    /** 게시글 상세보기 */
    public DataResponse<PostDetailResponse> getPost(Long postId) {
        UserEntity user = userAuthHolder.current();
        PostEntity post = postService.findById(postId);
        boolean isLiked = postService.isPostLikedByUser(postId, user.getId());
        return DataResponse.ok("게시글 조회 성공", PostDetailResponse.fromEntity(post, isLiked));
    }

    /** 게시글 좋아요 토글 */
    public Response togglePostLike(Long postId) {
        UserEntity user = userAuthHolder.current();
        postService.togglePostLike(postId, user.getId());
        return Response.ok("게시글 좋아요 토글 성공");
    }

    /** 이미지 업로드 */
    public DataResponse<String> uploadImage(MultipartFile image) {
        try {
            String imageUrl = s3Service.uploadImage(image);
            return DataResponse.ok("이미지 업로드 성공", imageUrl);
        } catch (IOException e) {
            throw new ImageUploadException();
        }
    }
}
