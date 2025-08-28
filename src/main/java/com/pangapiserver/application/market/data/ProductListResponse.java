package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;

public record ProductListResponse (
    String image,
    String name,
    int price
) {
    public static ProductListResponse of(ProductEntity entity) {
        return new ProductListResponse(
            entity.getImageUrl(),
            entity.getName(),
            entity.getPrice()
        );
    }
}