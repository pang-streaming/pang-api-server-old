package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;

import java.util.UUID;

public record ProductListResponse (
    UUID id,
    String image,
    String name,
    int price
) {
    public static ProductListResponse of(ProductEntity entity) {
        return new ProductListResponse(
            entity.getId(),
            entity.getImageUrl(),
            entity.getName(),
            entity.getPrice()
        );
    }
}