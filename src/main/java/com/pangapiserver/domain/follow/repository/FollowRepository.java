package com.pangapiserver.domain.follow.repository;

import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Integer>, FollowCustomRepository {
    List<FollowEntity> findByUser(UserEntity user);

    List<FollowEntity> findByFollower(UserEntity user);

    Optional<FollowEntity> findByUserAndFollower(UserEntity user, UserEntity follower);
}
