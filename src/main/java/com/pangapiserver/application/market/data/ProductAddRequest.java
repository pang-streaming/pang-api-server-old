package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.enumeration.ProductCategory;
import com.pangapiserver.domain.market.enumeration.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ProductAddRequest(
        @NotBlank UUID storeId,
        @NotBlank String image,
        @NotBlank String name,
        @NotBlank String description,
        @PositiveOrZero int price,
        @NotBlank String fileUrl,
        @NotBlank ProductCategory category,
        @NotBlank ProductType type
        ) {}