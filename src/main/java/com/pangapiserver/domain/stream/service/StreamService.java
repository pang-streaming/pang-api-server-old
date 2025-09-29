package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.exception.StreamNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.WatchHistoryEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;
    private final WatchHistoryRepository watchHistoryRepository;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    public StreamEntity getByStreamId(UUID streamId, UserEntity user) {
        StreamEntity stream = repository.findById(streamId)
            .orElseThrow(StreamNotFoundException::new);
        if (stream.getEndAt() != null) {
            WatchHistoryEntity entity = WatchHistoryEntity.builder()
                    .user(user)
                    .stream(stream)
                    .createdAt(LocalDateTime.now())
                    .build();
            if (watchHistoryRepository.existsByStreamAndUser(stream, user)) {
                watchHistoryRepository.deleteByStreamAndUser(stream, user);
            }

            watchHistoryRepository.save(entity);
        }
        return stream;
    }

    public List<StreamEntity> getLiveStreams() {
        return repository.findByEndAtIsNull();
    }

    public StreamEntity getLiveStreamByUserId(UserEntity user) {
        return repository.findByUserAndEndAtNull(user)
            .orElseThrow(StreamNotFoundException::new);
    }

    public void save(StreamEntity stream) {
        repository.save(stream);
    }

    public void update(StreamEntity stream) {
        stream.updateEndAt();
        repository.save(stream);
    }
}
