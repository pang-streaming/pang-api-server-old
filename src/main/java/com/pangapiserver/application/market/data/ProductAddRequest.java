package com.pangapiserver.application.market.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductAddRequest(
    @NotBlank String image,
    @NotBlank String name,
    @NotBlank String description,
    @PositiveOrZero int price,
    @NotBlank String fileUrl
) {}