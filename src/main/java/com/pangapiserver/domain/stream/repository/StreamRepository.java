package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StreamRepository extends JpaRepository<StreamEntity, UUID> {
    List<StreamEntity> findAllByOrderByIdDesc();

    List<StreamEntity> findByEndAtIsNull();

    List<StreamEntity> findAllByStatus(StreamStatus status);

    Optional<StreamEntity> findByUserAndEndAtNull(UserEntity user);

    Optional<StreamEntity> findByUserAndStatus(UserEntity user, StreamStatus status);

    List<StreamEntity> findAllByCategory(CategoryEntity category);

    List<StreamEntity> findAllByCategoryAndStatus(CategoryEntity category, StreamStatus status);

    List<StreamEntity> findByEndAtIsNotNullAndUser(UserEntity user);

    List<StreamEntity> findAllByStatusOrderByIdDesc(StreamStatus status);
}
