package com.ortakciemrecan.common.config;

import com.ortakciemrecan.common.dto.ExceptionResponse;
import com.ortakciemrecan.common.exception.CourierNotActiveException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(CourierNotActiveException.class)
    public ResponseEntity<ExceptionResponse> handleCourierNotActiveException(CourierNotActiveException ex, Locale locale) {
        String errorMessage = messageSource.getMessage("courier.not.active", null, locale);
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}