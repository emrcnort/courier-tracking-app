package com.ortakciemrecan.courier.service;

import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import com.ortakciemrecan.courier.repository.CourierStoreEntryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierStoreEntryLogService {
    private final CourierStoreEntryLogRepository repository;
    public void save(CourierStoreEntryLog courierStoreEntryLog) {
        repository.save(courierStoreEntryLog);
    }

    public int getTotalEntranceByCourierIdAndStoreName(Long courierId, String storeName) {
        return repository.findTotalEntranceByCourierIdAndStoreName(courierId, storeName)
                .orElse(0);
    }
}
