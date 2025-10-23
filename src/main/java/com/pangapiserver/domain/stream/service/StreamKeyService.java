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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

    public String createTemp(UserEntity user, String webRtcUrl, String key) {
        StreamKeyEntity streamKey = repository.findByUser(user)
            .orElse(StreamKeyEntity.create(user, webRtcUrl));
        streamKey.updateKey(aesEncoder.encrypt(key));
        streamKey.updateType(StreamType.WHIP);
        repository.save(streamKey);
        return key;
    }

    public String getByUser(UserEntity user) {
        StreamKeyEntity stream = repository.findByUser(user).orElse(null);
        if (stream == null) {
            return null;
        }
        log.warn("222222");
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
