package com.ortakciemrecan.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class CourierLocationEvent {
    private UUID courierId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
