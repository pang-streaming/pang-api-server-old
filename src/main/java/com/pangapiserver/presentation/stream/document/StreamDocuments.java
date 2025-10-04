package com.pangapiserver.presentation.stream.document;

import com.pangapiserver.application.stream.data.response.StreamInfoResponse;
import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.stream.data.response.StreamUserResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Tag(name = "스트리밍 Api", description = "사용자 스트리밍 api")
public interface StreamDocuments {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "라이브중인 스트리밍 조회 API", description = "현재 라이브중인 스트리밍들을 조회합니다.")
    DataResponse<List<StreamResponse>> getLiveStreams();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카테고리별 스트리밍 조회 API", description = "카테고리에 따라 스트리밍을 조회합니다.")
    DataResponse<List<StreamResponse>> getStreamsByCategory(Long categoryId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "특정 스트리밍 조회 API", description = "아이디에 따라 스트리밍을 조회합니다.")
    DataResponse<StreamInfoResponse> getStream(UUID streamId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로우하고 있는 스트리밍 조회 API", description = "자신이 팔로우 중인 스트리머들의 방송들을 조회합니다.")
    DataResponse<List<StreamResponse>> getFollowingLiveStreams();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스트리밍 키 조회 API", description = "자신의 스트리밍 키를 조회합니다.")
    DataResponse<StreamKeyResponse> getKey();

    @Operation(summary = "스트리밍 키 생성 API", description = "자신의 스트리밍 키를 생성합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    DataResponse<StreamKeyResponse> createKey();

    @Operation(summary = "스트리밍 키로 스트리밍 생성", description = "암호화 되지 않은 스트리밍 키로 스트림을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<StreamUserResponse> createStreamByKey(String key);

    @Operation(summary = "스트리밍 정보 수정", description = "스트리밍 정보를 수정합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<StreamInfoResponse> updateStream(UUID streamId, com.pangapiserver.application.stream.data.request.UpdateStreamRequest request);
}