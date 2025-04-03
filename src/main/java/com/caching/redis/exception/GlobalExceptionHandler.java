package com.caching.redis.exception;

import com.caching.redis.models.dto.ErrorResponseDto;
import com.caching.redis.utilities.MessageUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtilities messageUtilities;


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {

        Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorsList = ex.getBindingResult().getAllErrors();

        validationErrorsList.forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });

        ErrorResponseDto error = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode("FIELD_VALIDATION_ERROR")
                .errorMessage(messageUtilities.getMessage("FIELD_VALIDATION_ERROR"))
                .errorTime(LocalDateTime.now())
                .validationErrors(validationErrors)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest webRequest) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode("INTERNAL_SERVER_ERROR")
                .errorMessage(messageUtilities.getMessage("INTERNAL_SERVER_ERROR"))
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto error = ErrorResponseDto.builder()
                .apiPath(webRequest.getDescription(false))
                .errorCode(ex.getMessage())
                .errorMessage(messageUtilities.getMessage(ex.getMessage()))
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
