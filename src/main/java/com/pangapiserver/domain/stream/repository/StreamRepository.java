package com.pangapiserver.domain.stream.repository;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamRepository extends JpaRepository<StreamEntity, Integer> {
    List<StreamEntity> findAllByOrderByIdDesc();
}
