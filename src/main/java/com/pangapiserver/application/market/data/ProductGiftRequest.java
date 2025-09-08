package com.pangapiserver.application.market.data;

import java.util.UUID;

public record ProductGiftRequest (
    UUID productId,
    String username
) {
}
