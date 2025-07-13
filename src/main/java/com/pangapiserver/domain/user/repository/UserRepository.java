package com.pangapiserver.domain.user.repository;

import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    List<UserEntity> findAllByIdNot(UUID id);
}
