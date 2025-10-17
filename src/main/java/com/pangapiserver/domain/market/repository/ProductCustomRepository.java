package com.pangapiserver.domain.market.repository;

import com.pangapiserver.application.market.data.ProductWithLikeStatusDto;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;

public interface ProductCustomRepository {
    List<ProductEntity> findTop5ByOrderByLikesDesc();

    List<ProductWithLikeStatusDto> findAllWithLikeStatus(UserEntity user);
}
