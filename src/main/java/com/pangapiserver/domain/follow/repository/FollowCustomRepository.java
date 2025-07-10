package com.pangapiserver.domain.follow.repository;

import java.util.Map;
import java.util.List;
import java.util.UUID;

public interface FollowCustomRepository {
    Map<UUID, Long> countByFollowerIds(List<UUID> ids);
}
