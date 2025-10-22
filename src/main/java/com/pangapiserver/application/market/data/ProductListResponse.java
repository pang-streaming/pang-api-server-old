package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.document.ProductDocument;
import com.pangapiserver.domain.market.entity.ProductEntity;

import java.util.UUID;

public record ProductListResponse (
    UUID id,
    String image,
    String name,
    String description,
    int price,
    boolean isLiked
) {
    public static ProductListResponse of(ProductEntity entity, boolean isLiked) {
        return new ProductListResponse(
            entity.getId(),
            entity.getImageUrl(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            isLiked
        );
    }

    public static ProductListResponse of(ProductDocument entity, boolean isLiked) {
        return new ProductListResponse(
                entity.getId(),
                entity.getImage(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                isLiked
        );
    }
}