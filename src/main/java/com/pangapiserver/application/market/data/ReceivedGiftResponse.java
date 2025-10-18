package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.PurchaseEntity;
import com.pangapiserver.domain.market.enumeration.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReceivedGiftResponse(
    UUID purchaseId,
    UUID productId,
    String imageUrl,
    String productName,
    int price,
    String fileUrl,
    UUID buyerId,
    String buyerName,
    String address,
    String email,
    DeliveryStatus deliveryStatus,
    LocalDateTime createdAt
) {
    public static ReceivedGiftResponse of(PurchaseEntity entity) {
        return new ReceivedGiftResponse(
            entity.getId(),
            entity.getProduct().getId(),
            entity.getProduct().getImageUrl(),
            entity.getProduct().getName(),
            entity.getProduct().getPrice(),
            entity.getProduct().getFileUrl(),
            entity.getBuyer().getId(),
            entity.getBuyer().getUsername(),
            entity.getAddress(),
            entity.getEmail(),
            entity.getDeliveryStatus(),
            entity.getCreatedAt()
        );
    }
}

