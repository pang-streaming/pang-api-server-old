package com.pangapiserver.domain.follow.repository;

import java.util.List;
import java.util.UUID;

public interface FollowCustomRepository {
    List<Object[]> countByFollowerIds(List<UUID> ids);
}
