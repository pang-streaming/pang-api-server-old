package com.pangapiserver.presentation.community;

import com.pangapiserver.application.community.PostUseCase;
import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.community.document.CommunityDocuments;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommunityController implements CommunityDocuments {
    private final PostUseCase postUseCase;

    @Override
    @PostMapping()
    public Response addPost(@Valid @RequestBody AddPostRequest request) {
        return postUseCase.addPost(request);
    }


}
