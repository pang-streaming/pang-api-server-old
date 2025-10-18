package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;

import java.util.UUID;

public record ProductDetailResponse (
    String image,
    String name,
    int likes,
    int price,
    UUID userId,
    String profileImage,
    String username
) {
    public static ProductDetailResponse of(ProductEntity entity, int likes) {
        return new ProductDetailResponse(
            entity.getImageUrl(),
            entity.getName(),
            likes,
            entity.getPrice(),
            entity.getStore().getId(),
            entity.getStore().getProfileImage(),
            entity.getStore().getName()
        );
    }
}
