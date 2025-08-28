package com.pangapiserver.application.market;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.application.market.data.ProductDetailResponse;
import com.pangapiserver.application.market.data.ProductLikeRequest;
import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.enumeration.LikeStatus;
import com.pangapiserver.domain.market.service.MarketService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MarketUseCase {
    private final UserAuthenticationHolder holder;
    private final MarketService service;

    public Response add(ProductAddRequest request) {
        service.saveProduct(holder.current(), request);
        return Response.ok("상품 추가 성공");
    }

    public DataResponse<List<ProductListResponse>> getItems() {
        List<ProductEntity> items = service.getItems();
        List<ProductListResponse> responses = items.stream()
            .map(ProductListResponse::of).toList();
        return DataResponse.ok("상품 목록 조회 성공", responses);
    }

    public DataResponse<ProductDetailResponse> getItem(UUID productId) {
        ProductEntity entity = service.getById(productId);
        int likes = service.getLikes(productId);
        ProductDetailResponse response = ProductDetailResponse.of(entity, likes);
        return DataResponse.ok("상품 상세 조회 성공", response);
    }

    public Response like(ProductLikeRequest request) {
        UUID productId = request.productId();
        ProductEntity product = service.getById(productId);
        UserEntity user = holder.current();
        if (service.saveLike(user, product) == LikeStatus.LIKED) {
            return Response.ok("하트 성공");
        }
        return Response.ok("언 하트 성공");
    }
}
