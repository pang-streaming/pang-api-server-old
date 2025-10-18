package com.pangapiserver.presentation.store.document;

import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.store.data.CreateStoreRequest;
import com.pangapiserver.application.store.data.StoreResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Tag(name = "Store Api", description = "스토어 api")
public interface StoreDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스토어 생성 API", description = "스토어를 생성합니다.")
    Response createStore(CreateStoreRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스토어 목록 조회 API", description = "스토어 목록을 조회합니다.")
    DataResponse<List<StoreResponse>> getStores();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스토어 가입 API", description = "스토어 id로 스토어에 가입합니다.")
    Response joinStore(UUID storeId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "특정 스토어의 상품 목록 조회 API", description = "스토어 id로 상품 목록을 조회합니다.")
    DataResponse<List<ProductListResponse>> getStoreProducts(UUID storeId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "스토어 탈퇴 API", description = "스토어 id로 해당 스토어를 탈퇴합니다.")
    Response leaveStore(UUID storeId);
}
