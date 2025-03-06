package com.ortakciemrecan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class CourierStoreEntryLog {
    @Id
    @GeneratedValue
    private Long id;
    private UUID courierId;
    private String storeName;
    private LocalDateTime entryTime;
}