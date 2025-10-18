package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.PurchaseEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID>, PurchaseCustomRepository {
    boolean existsByBuyerAndProduct(UserEntity buyer, ProductEntity product);
    
    List<PurchaseEntity> findByBuyer(UserEntity buyer);
    
    List<PurchaseEntity> findByReceiver(UserEntity receiver);
}
