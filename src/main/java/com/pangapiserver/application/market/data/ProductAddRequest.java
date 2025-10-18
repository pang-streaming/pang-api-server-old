package com.pangapiserver.application.market.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ProductAddRequest(
    @NotBlank UUID storeId,
    @NotBlank String image,
    @NotBlank String name,
    @NotBlank String description,
    @PositiveOrZero int price,
    @NotBlank String fileUrl
) {}