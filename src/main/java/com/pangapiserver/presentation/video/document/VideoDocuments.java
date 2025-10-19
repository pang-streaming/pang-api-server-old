package com.pangapiserver.presentation.video.document;

import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Video", description = "동영상 API")
public interface VideoDocuments {
    @Operation(summary = "최근 시청한 동영상 조회", description = "최근 시청한 동영상 목록을 조회합니다.")
    DataResponse<List<StreamResponse>> getRecent();

    @Operation(summary = "스트리머의 생방송 조회", description = "스트리머의 생방송을 조회합니다.")
    DataResponse<StreamResponse> getLiveStreamByUsername(String username);

    @Operation(summary = "스트리머의 녹화된 동영상 조회", description = "스트리머의 녹화된 동영상 목록을 조회합니다.")
    DataResponse<List<StreamResponse>> getVideoByUsername(String username);

    @Operation(summary = "카테고리별 동영상 조회", description = "카테고리별로 종료된 동영상 목록을 조회합니다.")
    DataResponse<List<StreamResponse>> getEndedVideosByCategory(Long categoryId);
}