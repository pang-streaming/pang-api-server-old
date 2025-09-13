package com.pangapiserver.presentation.community;

import com.pangapiserver.application.community.PostUseCase;
import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.community.document.CommunityDocuments;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommunityController implements CommunityDocuments {
    private final PostUseCase postUseCase;

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
    public DataResponse<PostEntity> getPost(@RequestParam Long postId) {
        return postUseCase.getPost(postId);
    }
}
