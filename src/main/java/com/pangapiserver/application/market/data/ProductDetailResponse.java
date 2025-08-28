package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;

public record ProductDetailResponse (
    String image,
    String name,
    int likes,
    int price,
    String profileImage,
    String username
) {
    public static ProductDetailResponse of(ProductEntity entity, int likes) {
        return new ProductDetailResponse(
            entity.getImage(),
            entity.getName(),
            likes,
            entity.getPrice(),
            entity.getSeller().getProfileImage(),
            entity.getSeller().getUsername()
        );
    }
}
