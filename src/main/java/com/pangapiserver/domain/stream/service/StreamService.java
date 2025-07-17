package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.exception.StreamNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    public StreamEntity getByStreamId(UUID streamId) {
        return repository.findById(streamId)
            .orElseThrow(StreamNotFoundException::new);
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
