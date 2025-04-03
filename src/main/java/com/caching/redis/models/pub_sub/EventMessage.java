package com.caching.redis.models.pub_sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventMessage implements Serializable {
    private String eventType;
    private String eventPayload; // Ensure this matches what you're trying to access

    @Override
    public String toString() {
        return "EventMessage{eventType='" + eventType + "', eventPayload='" + eventPayload + "'}";
    }
}

