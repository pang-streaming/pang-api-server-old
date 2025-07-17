package com.pangapiserver.application.follow;

import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.domain.follow.service.FollowService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FollowUseCase {
    private final FollowService service;
    private final UserAuthenticationHolder userAuthHolder;

    public DataResponse<List<FollowingResponse>> getFollowings(String username) {
        return DataResponse.ok("팔로잉 조회 성공", service.getByFollowing(
                        username == null
                                ? userAuthHolder.current().getUsername()
                                : username
                )
        );
    }

    public DataResponse<List<FollowingResponse>> getFollowers(String username) {
        return DataResponse.ok("팔로워 조회 성공", service.getByFollower(
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
