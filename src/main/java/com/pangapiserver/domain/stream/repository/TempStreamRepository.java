package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.stream.entity.TempStreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TempStreamRepository extends JpaRepository<TempStreamEntity, UUID> {
    Optional<TempStreamEntity> findByUser(UserEntity user);
}
