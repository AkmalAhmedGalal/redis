package com.caching.redis.models.pub_sub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component //to be able to inject this service
@Log4j2
public class EventSubscriber implements MessageListener {

    private  final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // Convert the message body to a string
            String messageBody = new String(message.getBody());

            // Deserialize JSON string into EventMessage object
            EventMessage event = objectMapper.readValue(messageBody, EventMessage.class);

            // Log the event type and payload
            log.warn("Event Channel: " + event.getEventType() + " | Event Payload: " + event.getEventPayload());
        }catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}
