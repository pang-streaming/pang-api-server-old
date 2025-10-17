package com.pangapiserver.application.market.data;

import com.pangapiserver.domain.market.entity.ProductEntity;
import lombok.Getter;

@Getter
public class ProductWithLikeStatusDto {
    private final ProductEntity product;
    private final boolean isLiked;

    public ProductWithLikeStatusDto(ProductEntity product, boolean isLiked) {
        this.product = product;
        this.isLiked = isLiked;
    }
}
