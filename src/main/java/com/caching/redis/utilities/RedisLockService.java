package com.caching.redis.utilities;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisLockService {

    private final  StringRedisTemplate redisTemplate;

    //TODO:: this function need to be updated.
    //this function is used for ensure there is no race condition will happen
    public boolean acquireLock(String lockKey, long timeout) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, "locked", timeout, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success); // Returns true if lock is acquired
    }

    // Release the lock
    public void releaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}
