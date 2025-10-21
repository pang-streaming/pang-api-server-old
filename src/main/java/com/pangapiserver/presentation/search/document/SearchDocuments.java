package com.pangapiserver.presentation.search.document;

import com.pangapiserver.application.search.data.TotalSearchResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Search Api", description = "검색 api")
public interface SearchDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "통합 검색 API", description = "키워드로 방송, 유저, 상품 목록을 검색해 조회합니다.")
    DataResponse<TotalSearchResponse> search(String keyword);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "리인덱싱 API", description = "새로운 document 를 만들었을 때 기존에 있던 db 정보들을 document 로 리인덱싱 합니다.")
    Response reindex();
}
