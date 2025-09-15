package com.pangapiserver.presentation.community;

import com.pangapiserver.application.community.CommentUseCase;
import com.pangapiserver.application.community.PostUseCase;
import com.pangapiserver.application.community.data.AddCommentRequest;
import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.CommentResponse;
import com.pangapiserver.application.community.data.PostDetailResponse;
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.community.document.CommunityDocuments;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommunityController implements CommunityDocuments {
    private final PostUseCase postUseCase;
    private final CommentUseCase commentUseCase;

    @Override
    @PostMapping
    public Response addPost(@Valid @RequestBody AddPostRequest request) {
        return postUseCase.addPost(request);
    }

    @Override
    @GetMapping("/{communityId}")
    public DataResponse<Page<PostListResponse>> getPostsByCommunity(@PathVariable Long communityId, Pageable pageable, @RequestParam(defaultValue = "ALL") PostFilterType filter) {
        return postUseCase.getPostsByCommunity(communityId, pageable, filter);
    }

    @Override
    @GetMapping
    public DataResponse<PostDetailResponse> getPost(@RequestParam Long postId) {
        return postUseCase.getPost(postId);
    }

    @Override
    @PostMapping("/like/{postId}")
    public Response togglePostLike(@PathVariable Long postId) {
        return postUseCase.togglePostLike(postId);
    }

    @Override
    @PostMapping("/comment")
    public Response addComment(@Valid @RequestBody AddCommentRequest request) {
        return commentUseCase.addComment(request);
    }

    @Override
    @GetMapping("/comment/{postId}")
    public DataResponse<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return commentUseCase.getComments(postId);
    }

    @Override
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DataResponse<String> uploadImage(@RequestPart MultipartFile image) {
        return postUseCase.uploadImage(image);
    }
}
