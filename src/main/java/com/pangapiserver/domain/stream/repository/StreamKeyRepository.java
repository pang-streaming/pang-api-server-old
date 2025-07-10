package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamKeyRepository extends JpaRepository<StreamKeyEntity, Integer> {
    StreamKeyEntity findByUser(UserEntity user);
}