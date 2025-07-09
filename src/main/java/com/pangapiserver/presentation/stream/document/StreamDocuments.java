package com.pangapiserver.presentation.stream.document;

import com.pangapiserver.application.stream.data.StreamListResponse;
import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "스트리밍 Api", description = "사용자 스트리밍 api")
public interface StreamDocuments {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스트리밍 목록 API", description = "스트리밍 목록을 조회합니다")
    List<StreamListResponse> getItems();


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로잉 조회 API", description = "자신이 팔로우하는 사람을 가져옵니다.")
    List<StreamListResponse> getPopularItems();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "팔로잉 조회 API", description = "자신이 팔로우하는 사람을 가져옵니다.")
    Response addItem(@RequestParam String title);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스트리밍 키 조회 API", description = "자신의 스트리밍 키를 조회합니다.")
    DataResponse<StreamKeyResponse> getKey();

    @Operation(summary = "스트림이 키 생성 API", description = "자신의 스트리밍 키를 생성합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    DataResponse<StreamKeyResponse> createKey();
}