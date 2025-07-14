package com.pangapiserver.presentation.follow;

import com.pangapiserver.application.follow.FollowUseCase;
import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.follow.document.FollowDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController implements FollowDocuments {
    private final FollowUseCase useCase;

    @Override
    @GetMapping("/following")
    public DataResponse<List<FollowingResponse>> getFollowings(@RequestParam(name = "id", required = false) UUID id) {
        return useCase.getFollowings(id);
    }

    @Override
    @GetMapping("/follower")
    public DataResponse<List<FollowingResponse>> getFollowers(@RequestParam(name = "id", required = false) UUID id) {
        return useCase.getFollowers(id);
    }

    @Override
    @PostMapping
    public Response followOrUnfollow(@RequestParam("id") UUID id) {
        return useCase.followOrUnfollow(id);
    }
}
