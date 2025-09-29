package com.pangapiserver.infrastructure.redis.support;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
    private final StringRedisTemplate redisTemplate;

    public void save(SaveType type, String key, String value) {
        redisTemplate.opsForValue().set(type.getValue().concat(key), value);
    }

    public String get(SaveType type, String key) {
        return redisTemplate.opsForValue().get(type.getValue().concat(key));
    }

    public void delete(SaveType type, String key) {
        redisTemplate.delete(type.getValue().concat(key));
    }
}
