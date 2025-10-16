package com.pangapiserver.domain.interest.repository;

import com.pangapiserver.domain.interest.entity.InterestEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<InterestEntity, Long>, InterestCustomRepository {
    void deleteAllByUser(UserEntity user);
}
