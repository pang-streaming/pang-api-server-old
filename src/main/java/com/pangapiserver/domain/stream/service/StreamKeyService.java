package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.repository.StreamKeyRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamKeyService {
    private final StreamKeyRepository repository;

    public StreamKeyEntity create(UserEntity user) {
        StreamKeyEntity streamKey = StreamKeyEntity.create(user);
        return repository.save(streamKey);
    }

    public StreamKeyEntity findByUser(UserEntity user) {
        return repository.findByUser(user);
    }
}
