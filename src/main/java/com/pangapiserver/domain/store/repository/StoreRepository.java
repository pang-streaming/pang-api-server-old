package com.pangapiserver.domain.store.repository;

import com.pangapiserver.domain.store.entity.StoreEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
    List<StoreEntity> findByUser(UserEntity user);
}
