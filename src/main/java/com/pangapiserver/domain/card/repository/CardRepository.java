package com.pangapiserver.domain.card.repository;

import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findByUser(UserEntity user);


}
