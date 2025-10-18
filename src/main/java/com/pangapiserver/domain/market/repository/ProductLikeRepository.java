package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.ProductLikeEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductLikeRepository extends JpaRepository<ProductLikeEntity, UUID>, ProductLikeCustomRepository {
    int countByProduct_Id(UUID productId);

    Optional<ProductLikeEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
}
