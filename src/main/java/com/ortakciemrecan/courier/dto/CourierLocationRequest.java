package com.ortakciemrecan.courier.dto;

import java.time.LocalDateTime;
public record CourierLocationRequest(
        Long courierId,
        LocalDateTime timestamp,
        Double latitude,
        Double longitude
) {}
