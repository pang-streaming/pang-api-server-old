package com.pangapiserver.application.market.data;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductGiftRequest (
    @NotNull UUID productId,
    @NotNull String username
) {
}
