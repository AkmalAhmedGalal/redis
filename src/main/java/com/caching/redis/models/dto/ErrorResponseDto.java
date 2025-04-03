package com.caching.redis.models.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponseDto {

    private String apiPath;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
    private Map<String, String> validationErrors;
}
