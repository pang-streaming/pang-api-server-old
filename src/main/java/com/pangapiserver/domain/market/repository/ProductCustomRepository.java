package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;

import java.util.List;

public interface ProductCustomRepository {
    List<ProductEntity> findTop5ByOrderByLikesDesc();
}
