package com.pangapiserver.presentation.follow;

import com.pangapiserver.application.follow.FollowUseCase;
import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.follow.document.FollowDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController implements FollowDocuments {
    private final FollowUseCase useCase;

    @Override
    @GetMapping("/following")
    public DataResponse<List<FollowingResponse>> getFollowings(@RequestParam(name = "username", required = false) String username) {
        return useCase.getFollowings(username);
    }

    @Override
    @GetMapping("/follower")
    public DataResponse<List<FollowingResponse>> getFollowers(@RequestParam(name = "username", required = false) String username) {
        return useCase.getFollowers(username);
    }

    @Override
    @PostMapping
    public Response followOrUnfollow(@RequestParam("username") String username) {
        return useCase.followOrUnfollow(username);
    }
}
