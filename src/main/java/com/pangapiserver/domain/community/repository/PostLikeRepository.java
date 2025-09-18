package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findByUser_IdAndPost_Id(UUID userId, Long postId);
}
