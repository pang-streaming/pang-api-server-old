package com.pangapiserver.presentation.market.document;

import com.pangapiserver.application.market.data.*;
import com.pangapiserver.domain.market.enumeration.ProductCategory;
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

@Tag(name = "Market Api", description = "마켓 api")
public interface MarketDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 추가 API", description = "상품을 추가합니다.")
    Response add(ProductAddRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 목록 조회 API", description = "상품을 목록을 조회합니다.")
    DataResponse<Page<ProductListResponse>> getItems(Pageable pageable);

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
    @Operation(summary = "받은 선물 조회 API", description = "자신이 받은 선물 목록을 조회합니다. 선물 보낸 사람 정보를 포함합니다.")
    DataResponse<List<ReceivedGiftResponse>> getReceivedGifts();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "선물 정보 수정 API", description = "받은 선물의 주소나 배송 상태를 수정합니다.")
    Response updateGift(GiftUpdateRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "인기 상품 API", description = "인기 상품 목록을 조회합니다.")
    DataResponse<List<ProductListResponse>> getTop5Products();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카테고리별 상품 목록 조회 API", description = "카테고리 별로 상품 목록을 조회합니다.")
    DataResponse<List<ProductListResponse>> getItemsByCategory(ProductCategory category);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 삭제 API", description = "상품 id 로 상품을 삭제합니다.")
    Response deleteProduct(UUID productId);
}
