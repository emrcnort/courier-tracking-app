package com.ortakciemrecan.dto;

import java.time.LocalDateTime;
public record CourierLocationRequest(
        Long courierId,
        LocalDateTime timestamp,
        Double latitude,
        Double longitude
) {}
