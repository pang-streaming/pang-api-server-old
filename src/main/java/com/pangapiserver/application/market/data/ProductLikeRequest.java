package com.pangapiserver.application.market.data;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProductLikeRequest (
    @NotBlank
    UUID productId
) {}
