package com.ortakciemrecan.service;

import com.ortakciemrecan.entity.CourierStoreEntryLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ortakciemrecan.repository.CourierStoreEntryLogRepository;

@Service
@RequiredArgsConstructor
public class CourierStoreEntryLogService {
    private final CourierStoreEntryLogRepository repository;
    public void save(CourierStoreEntryLog courierStoreEntryLog) {
        repository.save(courierStoreEntryLog);
    }
}
