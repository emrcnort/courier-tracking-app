package com.ortakciemrecan.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
}
