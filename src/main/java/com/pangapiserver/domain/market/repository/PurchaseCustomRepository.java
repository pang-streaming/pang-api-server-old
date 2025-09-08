package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;

public interface PurchaseCustomRepository {
    List<ProductEntity> findProductByBuyer(UserEntity buyer);
}
