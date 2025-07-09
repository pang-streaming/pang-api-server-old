package com.pangapiserver.infrastructure.redis.support;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public void save(SaveType type, String key, String value) {
        redisTemplate.opsForValue().set(type.getValue().concat(key), value);
    }

    public void get(SaveType type, String key) {
        redisTemplate.opsForValue().get(type.getValue().concat(key));
    }

    public void delete(SaveType type, String key) {
        redisTemplate.delete(type.getValue().concat(key));
    }
}
