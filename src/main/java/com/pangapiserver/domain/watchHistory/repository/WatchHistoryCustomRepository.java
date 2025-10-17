package com.pangapiserver.domain.watchHistory.repository;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface WatchHistoryCustomRepository {
    List<StreamEntity> getRecent(UserEntity user);
}
