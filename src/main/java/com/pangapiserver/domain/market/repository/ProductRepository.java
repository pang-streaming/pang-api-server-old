package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, ProductCustomRepository {
    List<ProductEntity> findByStore(StoreEntity store);
}
