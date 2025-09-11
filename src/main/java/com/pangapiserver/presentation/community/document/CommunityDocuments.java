package com.pangapiserver.presentation.community.document;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Community Api", description = "커뮤니티 api")
public interface CommunityDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 업로드", description = "게시글을 업로드합니다.")
    Response addPost(AddPostRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "전체게시물", description = "sort에는 \"createAt,desc\"를 넣으시면 됩니다")
    DataResponse<Page<PostListResponse>> getPostsByCommunity(Long communityId, Pageable pageable);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "게시글 상세보기", description = "게시글을 상세보기합니다")
    DataResponse<PostEntity> getPost(Long postId);
}
