package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.enumeration.ProductType;

import java.util.UUID;

public record ProductDetailResponse (
    String image,
    String name,
    int likes,
    int price,
    UUID storeId,
    String profileImage,
    String username,
    String description,
    ProductType type,
    boolean isLiked,
    boolean isPurchased,
    String fileUrl
) {
    public static ProductDetailResponse of(ProductEntity entity, int likes, boolean isLiked, boolean isPurchased) {
        return new ProductDetailResponse(
            entity.getImageUrl(),
            entity.getName(),
            likes,
            entity.getPrice(),
            entity.getStore().getId(),
            entity.getStore().getProfileImage(),
            entity.getStore().getName(),
            entity.getDescription(),
            entity.getType(),
            isLiked,
            isPurchased,
            entity.getFileUrl()
        );
    }
}
