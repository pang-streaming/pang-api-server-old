package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.enumeration.DeliveryStatus;

import java.util.UUID;

public record GiftUpdateRequest(
    UUID purchaseId,
    String address,
    DeliveryStatus deliveryStatus
) {
}

