package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.exception.StreamKeyNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamKeyRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.infrastructure.encode.Sha512Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamKeyService {
    private final StreamKeyRepository repository;

    public StreamKeyEntity create(UserEntity user) {
        StreamKeyEntity streamKey = repository.findByUser(user)
            .orElse(StreamKeyEntity.create(user));
        String key = Sha512Encoder.encode(Sha512Encoder.encode(user.getUsername()));
        streamKey.updateKey(key);
        return repository.save(streamKey);
    }

    public StreamKeyEntity getByUser(UserEntity user) {
        return repository.findByUser(user)
            .orElseThrow(UserNotFoundException::new);
    }

    public StreamKeyEntity getByStreamKey(String encodeKey) {
        return repository.findByKey(encodeKey)
                .orElseThrow(StreamKeyNotFoundException::new);
    }
}
