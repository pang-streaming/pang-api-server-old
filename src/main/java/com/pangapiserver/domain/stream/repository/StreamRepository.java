package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StreamRepository extends JpaRepository<StreamEntity, Integer> {
    List<StreamEntity> findAllByOrderByIdDesc();

    Optional<StreamEntity> findByUserAndEndAtNull(UserEntity user);
}
