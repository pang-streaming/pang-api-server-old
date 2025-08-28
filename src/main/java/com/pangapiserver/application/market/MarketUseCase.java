package com.pangapiserver.application.market;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.domain.market.service.MarketService;
import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketUseCase {
    private final MarketService service;

    public Response add(ProductAddRequest request) {
        return Response.ok("상품 추가 성공");
    }
}
