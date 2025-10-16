package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
    Optional<CommunityEntity> findByUser(UserEntity user);
}
