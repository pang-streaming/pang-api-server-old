package com.pangapiserver.infrastructure.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public int getViewCount(String username) {
        String viewCountStr = redisTemplate.opsForValue().get(username + ":count");
        if (viewCountStr == null) {
            return 0;
        }
        int viewCount = Integer.parseInt(viewCountStr) - 1;
        return Math.max(viewCount, 0);
    }

    public void deleteViewCount(String username) {
        redisTemplate.delete(username + ":count");
    }
}
