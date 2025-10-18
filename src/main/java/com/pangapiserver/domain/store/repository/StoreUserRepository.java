package com.pangapiserver.domain.store.repository;

import com.pangapiserver.domain.store.entity.StoreEntity;
import com.pangapiserver.domain.store.entity.StoreUserEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUserEntity, Long> {
    boolean existsByUserIdAndStoreId(UUID userId, UUID storeId);

    void deleteByStoreAndUser(StoreEntity store, UserEntity user);
}
