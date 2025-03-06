package com.ortakciemrecan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CourierLocation {
    @Id
    @GeneratedValue
    private Long id;
    private UUID courierId;
    private LocalDateTime timestamp;
    private Double latitude;
    private Double longitude;
}
