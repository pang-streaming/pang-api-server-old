package com.pangapiserver.domain.video.service;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.WatchHistoryEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final WatchHistoryRepository repository;
    private final StreamRepository streamRepository;

    public List<StreamEntity> getRecent(UserEntity user) {
        List<WatchHistoryEntity> histories = repository.findTop10ByUserOrderByCreatedAtDesc(user);
        List<StreamEntity> streams = histories.stream()
                .map(WatchHistoryEntity::getStream)
                .toList();
        List<UUID> ids = streams.stream()
                .map(StreamEntity::getId)
                .toList();
        return ids.stream()
                .map(streamRepository::findById)
                .flatMap(Optional::stream)
                .toList();
    }
}
