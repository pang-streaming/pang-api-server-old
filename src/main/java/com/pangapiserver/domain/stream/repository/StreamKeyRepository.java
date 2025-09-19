package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreamKeyRepository extends JpaRepository<StreamKeyEntity, Integer> {
    Optional<StreamKeyEntity> findByUser(UserEntity user);
    Optional<StreamKeyEntity> findByKey(String streamKey);
}