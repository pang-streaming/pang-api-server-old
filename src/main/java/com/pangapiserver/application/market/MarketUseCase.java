package com.pangapiserver.application.market;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.service.MarketService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
}
