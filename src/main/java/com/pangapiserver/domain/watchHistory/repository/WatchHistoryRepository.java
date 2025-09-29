package com.pangapiserver.domain.watchHistory.repository;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.WatchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistoryEntity, UUID>, WatchHistoryCustomRepository {
    boolean existsByStreamAndUser(StreamEntity stream, UserEntity user);

    void deleteByStreamAndUser(StreamEntity stream, UserEntity user);
}
