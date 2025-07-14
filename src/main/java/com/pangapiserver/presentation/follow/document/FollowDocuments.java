package com.pangapiserver.presentation.follow.document;

import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Tag(name = "Follow Api", description = "팔로우 api")
public interface FollowDocuments {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로잉 조회 API", description = "자신이 팔로우하는 사람을 가져옵니다.")
    DataResponse<List<FollowingResponse>> getFollowings(UUID id);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로워 조회 API", description = "자신을 팔로워를 가져옵니다.")
    DataResponse<List<FollowingResponse>> getFollowers(UUID id);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로우/언팔로우 API", description = "팔로우하거나 언팔로우합니다.")
    Response followOrUnfollow(UUID id);
}