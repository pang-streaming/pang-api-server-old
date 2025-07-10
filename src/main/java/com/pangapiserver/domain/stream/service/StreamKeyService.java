package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.repository.StreamKeyRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.encode.Sha512Encoder;
import com.pangapiserver.infrastructure.redis.support.RedisRepository;
import com.pangapiserver.infrastructure.redis.support.SaveType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamKeyService {
    private final StreamKeyRepository repository;
    private final RedisRepository redisRepository;

    public StreamKeyEntity create(UserEntity user) {
        String key = Sha512Encoder.encode(user.getUsername());
        StreamKeyEntity streamKey = StreamKeyEntity.create(user, key);
        redisRepository.save(SaveType.STREAM_KEY, key, user.getUsername());
        return repository.save(streamKey);
    }

    public StreamKeyEntity getByUser(UserEntity user) {
        return repository.findByUser(user);
    }
}
