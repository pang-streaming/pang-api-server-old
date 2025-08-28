package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductLikeRepository extends JpaRepository<ProductLikeEntity, UUID> {
    int countByProduct_Id(UUID productId);
}
