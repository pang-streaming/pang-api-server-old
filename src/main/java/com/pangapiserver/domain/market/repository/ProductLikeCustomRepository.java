package com.pangapiserver.domain.market.repository;

import java.util.List;
import java.util.UUID;

public interface ProductLikeCustomRepository {
    List<UUID> findLikedProductIdsByUserId(UUID userId);
}
