package com.caching.redis.models.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private String code;
    private String message;
    private String path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime timestamp;
    private List<FieldError> fieldErrors;
    public ExceptionResponse() {
        timestamp = LocalDateTime.now();
    }

    public ExceptionResponse(String code, String message, String path) {
        this();
        this.code = code;
        this.message = message;
        this.path = path;
        fieldErrors = new ArrayList<>();
    }

    public String toJsonString() {
        return "{\n" +
                "    \"code\": \"" + code + "\",\n" +
                "    \"message\": \"" + message + "\",\n" +
                "    \"timestamp\": \"" + timestamp + "\"\n" +
                "}";
    }
}