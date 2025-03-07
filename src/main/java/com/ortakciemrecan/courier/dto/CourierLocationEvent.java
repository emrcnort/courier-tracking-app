package com.ortakciemrecan.courier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CourierLocationEvent(
        @JsonProperty("courier")
        Long courierId,
        @JsonProperty("lat")
        Double latitude,
        @JsonProperty("lng")
        Double longitude) {
}

