package com.pangapiserver.application.market.data;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductLikeRequest (
    @NotNull
    UUID productId
) {}
