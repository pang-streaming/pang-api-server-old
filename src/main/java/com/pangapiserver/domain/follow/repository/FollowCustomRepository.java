package com.pangapiserver.domain.follow.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

public interface FollowCustomRepository {
    Map<UUID, Long> countByFollowerIds(List<UUID> ids);

    List<FollowEntity> findFollowingsWithFollowerByUser(UserEntity user);
}
