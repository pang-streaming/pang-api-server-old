package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;

import java.util.UUID;

public record PurchaseResponse (
    UUID productId,
    String imageUrl,
    String name,
    int price,
    String fileUrl
) {
    public static PurchaseResponse of(ProductEntity entity) {
        return new PurchaseResponse(
            entity.getId(),
            entity.getImageUrl(),
            entity.getName(),
            entity.getPrice(),
            entity.getFileUrl()
        );
    }
}
