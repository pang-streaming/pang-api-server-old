package com.pangapiserver.presentation.stream.document;

import com.pangapiserver.application.stream.data.request.UpdateStreamRequest;
import com.pangapiserver.application.stream.data.response.*;
import com.pangapiserver.domain.stream.entity.StreamType;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    DataResponse<StreamKeyResponse> createKey(StreamType type);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "내 방송 상태 API", description = "내 방송 상태를 확인합니다.cl")
    DataResponse<StreamStatusResponse> isStreaming();

    @Operation(summary = "스트리밍 키로 스트리밍 생성", description = "암호화 되지 않은 스트리밍 키로 스트림을 생성합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<StreamUserResponse> createStreamByKey(String key);

    @Operation(summary = "스트리밍 정보 수정", description = "스트리밍 정보를 수정합니다.")
    @ResponseStatus(HttpStatus.OK)
    Response updateStream(String key, UpdateStreamRequest request);

    @Operation(summary = "스트리밍 검색 API", description = "방송 제목을 검색해 방송 목록을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<Page<StreamResponse>> search(String keyword, Pageable pageable);

    @Operation(summary = "스트리밍 종료 API", description = "스트리밍을 종료합니다.")
    @ResponseStatus(HttpStatus.OK)
    Response closeStream(String key);

    @Operation(summary = "팔로우하는 유저의 종료된 방송 목록 조회 API", description = "팔로우하는 유저의 종료된 방송 목록을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<List<StreamResponse>> getEndedStreamsOfFollowings();

    @Operation(summary = "스트림 키로 스트리밍 정보 조회 API", description = "스트림 키를 통해 현재 라이브 중인 스트리밍 정보를 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    DataResponse<StreamInfoResponse> getStreamByKey(String key);
}