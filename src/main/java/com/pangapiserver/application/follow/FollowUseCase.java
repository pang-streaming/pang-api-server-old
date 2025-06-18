package com.pangapiserver.application.follow;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.Response;
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
        return DataResponse.ok(service.getByFollowing(
                        username == null
                                ? userAuthHolder.current().getUsername()
                                : username
                )
        );
    }

    public DataResponse<List<FollowingResponse>> getFollowers(String username) {
        return DataResponse.ok(service.getByFollower(
            username == null
                ? userAuthHolder.current().getUsername()
                : username
            )
        );
    }

    public Response followOrUnfollow(String username) {
        UserEntity user = userAuthHolder.current();
        service.followOrNot(user, username);
        return Response.ok("success");
    }
}
