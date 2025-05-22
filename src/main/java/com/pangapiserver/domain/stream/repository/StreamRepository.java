package com.pangapiserver.domain.stream.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StreamRepository extends JpaRepository<StreamEntity, Integer> {
    List<StreamEntity> findAllByOrderByIdDesc();
}
