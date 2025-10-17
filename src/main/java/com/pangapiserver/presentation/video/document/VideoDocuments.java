package com.pangapiserver.presentation.video.document;

import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "동영상 Api", description = "동영상 api")
public interface VideoDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "최근 시청한 동영상 조회 API", description = "최근 시청했던 동영상을 10개까지 조회합니다.")
    DataResponse<List<StreamResponse>> getRecent();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저의 생방송 조회 API", description = "유저의 현재 생방송을 조회합니다.")
    DataResponse<StreamResponse> getLiveStreamByUsername(@NotBlank String username);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "유저의 동영상 조회 API", description = "유저의 동영상들을 조회합니다.")
    DataResponse<List<StreamResponse>> getVideoByUsername(@NotBlank String username);
}