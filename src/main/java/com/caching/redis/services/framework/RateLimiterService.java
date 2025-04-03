package com.caching.redis.services.framework;

public interface RateLimiterService {

    public boolean isAllowed(String userId, int maxRequests, int timeWindowSeconds);

}
