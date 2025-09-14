package com.pangapiserver.presentation.community.document;

import com.pangapiserver.application.community.data.*;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Community Api", description = "커뮤니티 api")
public interface CommunityDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 업로드", description = "게시글을 업로드합니다.")
    Response addPost(AddPostRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시물 목록 조회", description = "sort에는 \"createAt,desc\"를 넣으시면 됩니다")
    DataResponse<Page<PostListResponse>> getPostsByCommunity(Long communityId, Pageable pageable, PostFilterType filter);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 상세보기", description = "게시글을 상세보기합니다")
    DataResponse<PostDetailResponse> getPost(Long postId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 좋아요")
    Response togglePostLike(Long postId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "댓글 작성", description = "댓글을 업로드합니다.")
    Response addComment(AddCommentRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "댓글 조회", description = "댓글을 조회합니다.")
    DataResponse<List<CommentResponse>> getComments(Long postId);
}
