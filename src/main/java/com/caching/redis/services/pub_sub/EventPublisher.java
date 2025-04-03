package com.caching.redis.services.pub_sub;

import com.caching.redis.models.pub_sub.EventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CHANNEL = "events-channel";

    //THE PUBLISHER
    public void publishEvent(String eventType, String eventPayload) {
        EventMessage eventMessage = new EventMessage(eventType, eventPayload);
        redisTemplate.convertAndSend(CHANNEL, eventMessage);
    }

}
