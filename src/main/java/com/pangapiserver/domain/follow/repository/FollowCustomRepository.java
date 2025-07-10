package com.pangapiserver.domain.follow.repository;

import com.pangapiserver.application.follow.data.FollowerCountResponse;

import java.util.List;
import java.util.UUID;

public interface FollowCustomRepository {
    List<FollowerCountResponse> countByFollowerIds(List<UUID> ids);
}
