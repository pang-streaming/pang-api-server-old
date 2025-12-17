package com.pangapiserver.application.market.data;

import java.util.UUID;

public record ProductBuyRequest(
    UUID productId,
    String address,
    String email,
    String phone
) {
}
