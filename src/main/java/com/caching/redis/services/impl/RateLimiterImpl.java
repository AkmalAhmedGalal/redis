package com.caching.redis.services.impl;

import com.caching.redis.services.framework.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class RateLimiterImpl implements RateLimiterService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean isAllowed(String userId, int maxRequests, int timeWindowSeconds) {
        String key = String.format("userID:%s", userId); // Unique key for the user

        // Try to increment the count of requests
        Long requestCount = stringRedisTemplate.opsForValue().increment(key);

        if (requestCount == null || requestCount == 1) {
            // Set expiry time for the key (first request in the time window)
            stringRedisTemplate.expire(key, Duration.ofSeconds(timeWindowSeconds));
        }

        // If the request count exceeds maxRequests, return false
        return requestCount != null && requestCount <= maxRequests;
    }

}
