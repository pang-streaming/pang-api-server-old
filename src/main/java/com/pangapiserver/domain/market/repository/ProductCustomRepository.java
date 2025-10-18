package com.pangapiserver.domain.market.repository;

import com.pangapiserver.application.market.data.ProductWithLikeStatusDto;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCustomRepository {
    List<ProductEntity> findTop5ByOrderByLikesDesc();

    Page<ProductWithLikeStatusDto> findAllWithLikeStatus(UserEntity user, Pageable pageable);
}
