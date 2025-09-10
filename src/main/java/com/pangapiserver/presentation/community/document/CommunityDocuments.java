package com.pangapiserver.presentation.community.document;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Community Api", description = "커뮤니티 api")
public interface CommunityDocuments {
    @ResponseStatus(HttpStatus.OK)
    Response addPost(AddPostRequest request);
}
