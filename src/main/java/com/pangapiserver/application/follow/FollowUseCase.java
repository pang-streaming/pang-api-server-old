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

    public DataResponse<List<FollowingResponse>> getFollowings(UUID id) {
        return DataResponse.ok("팔로잉 조회 성공", service.getByFollowing(
                        id == null
                                ? userAuthHolder.current().getId()
                                : id
                )
        );
    }

    public DataResponse<List<FollowingResponse>> getFollowers(UUID id) {
        return DataResponse.ok("팔로워 조회 성공", service.getByFollower(
            id == null
                ? userAuthHolder.current().getId()
                : id
            )
        );
    }

    public Response followOrUnfollow(UUID id) {
        UserEntity user = userAuthHolder.current();
        service.followOrNot(user, id);
        return Response.ok("success");
    }
}
