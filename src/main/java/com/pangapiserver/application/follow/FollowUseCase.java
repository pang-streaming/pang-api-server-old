package com.pangapiserver.application.follow;

import java.util.List;

import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.follow.service.FollowService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;

@Component
@RequiredArgsConstructor
public class FollowUseCase {
    private final FollowService service;
    private final UserAuthenticationHolder userAuthHolder;

    public DataResponse<List<FollowingResponse>> getFollowings(String username) {
        return DataResponse.ok(service.getByFollowing(isNull(username)));
    }

    public DataResponse<List<FollowingResponse>> getFollowers(String username) {
        return DataResponse.ok(service.getByFollower(isNull(username)));
    }

    public Response followOrUnfollow(String username) {
        UserEntity user = userAuthHolder.current();
        service.followOrNot(user, username);
        return Response.ok("success");
    }

    private String isNull(String username) {
        if (username == null) {
            username = userAuthHolder.current().getUsername();
        }
        return username;
    }
}
