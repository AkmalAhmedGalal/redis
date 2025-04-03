package com.caching.redis.interceptors;

import com.caching.redis.services.framework.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;


@Component
@RequiredArgsConstructor
@Log4j2
public class RateLimitInterceptor implements AsyncHandlerInterceptor {

    private final RateLimiterService rateLimiterService;
    @Value("${rate.limit.max_requests}")
    private int maxRequest;
    @Value("${rate.limit.time_window_seconds}")
    private int timeWindowSecond;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getRemoteAddr();
       log.warn(String.format("userID: %s",userId));
        if (!rateLimiterService.isAllowed(userId, maxRequest, timeWindowSecond)) {
            response.setStatus(429);
            response.getWriter().write("Too many requests. Please try again later.");
            return false;
        }
        return true;
    }
}
