package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPkPostIdOrderByCreatedAtAsc(Long postId);
}
