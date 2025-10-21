package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.entity.StreamType;
import com.pangapiserver.domain.stream.exception.StreamKeyNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamKeyRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.infrastructure.encode.AESEncoder;
import com.pangapiserver.infrastructure.encode.Sha512Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamKeyService {
    private final StreamKeyRepository repository;
    private final AESEncoder aesEncoder;

    public String create(UserEntity user, StreamType type) {
        StreamKeyEntity streamKey = repository.findByUser(user)
            .orElse(StreamKeyEntity.create(user));
        String key = Sha512Encoder.encode(user.getUsername());
        streamKey.updateKey(aesEncoder.encrypt(key));
        streamKey.updateType(type);
        repository.save(streamKey);
        return key;
    }

    public String getByUser(UserEntity user) {
        StreamKeyEntity stream = repository.findByUser(user)
            .orElseThrow(UserNotFoundException::new);
        return aesEncoder.decrypt(stream.getKey());
    }

    public StreamKeyEntity getByStreamKey(String key) {
        return repository.findByKey(aesEncoder.encrypt(key))
                .orElseThrow(StreamKeyNotFoundException::new);
    }

    public void updateStreamType(UserEntity user, StreamType type) {
        StreamKeyEntity streamKey = repository.findByUser(user)
            .orElseThrow(StreamKeyNotFoundException::new);
        streamKey.updateType(type);
        repository.save(streamKey);
    }
}
