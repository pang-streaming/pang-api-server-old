package com.pangapiserver.presentation.community.document;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.application.community.data.PostResponseDto;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Community Api", description = "커뮤니티 api")
public interface CommunityDocuments {
    @ResponseStatus(HttpStatus.OK)
    Response addPost(AddPostRequest request);

    @ResponseStatus(HttpStatus.OK)
    DataResponse<Page<PostResponseDto>> getPostsByCommunity(Long communityId, Pageable pageable);
}
