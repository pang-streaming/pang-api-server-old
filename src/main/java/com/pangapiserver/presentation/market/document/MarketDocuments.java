package com.pangapiserver.presentation.market.document;

import com.pangapiserver.application.market.data.*;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@Tag(name = "Market Api", description = "마켓 api")
public interface MarketDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 추가 API", description = "상품을 추가합니다.")
    Response add(ProductAddRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 목록 조회 API", description = "상품을 목록을 조회합니다.")
    DataResponse<List<ProductListResponse>> getItems();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 상세 조회 API", description = "상품의 상세 정보를 조회합니다.")
    DataResponse<ProductDetailResponse> getItem(UUID productId);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 좋아요 API", description = "상품을 좋아요합니다. 한번 더 하면 취소됩니다.")
    Response like(ProductLikeRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 구매 API", description = "상품을 구매합니다.")
    DataResponse<PurchaseResponse> purchase(ProductBuyRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 선물 API", description = "상품을 다른 유저에게 선물합니다.")
    Response gift(ProductGiftRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "선물 목록 조회 API", description = "자신이 받은 선물 목록을 조회합니다.")
    DataResponse<List<PurchaseResponse>> getGifts();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "구매 목록 조회 API", description = "자신의 구매 목록을 조회합니다. 배송 상태, 주소 등 상세 정보를 포함합니다.")
    DataResponse<List<PurchaseDetailResponse>> getPurchases();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "인기 상품 API", description = "인기 상품 목록을 조회합니다.")
    DataResponse<List<ProductListResponse>> getTop5Products();
}
