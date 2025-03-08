package com.ortakciemrecan.courier.service;

import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import com.ortakciemrecan.courier.repository.CourierStoreEntryLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierStoreEntryLogService {
    private final CourierStoreEntryLogRepository repository;
    @Transactional
    public void save(CourierStoreEntryLog courierStoreEntryLog) {
        repository.save(courierStoreEntryLog);
    }
    public double getTotalEntranceByCourierId(Long courierId) {
        return repository.findTotalEntranceByCourierId(courierId)
                .orElse(0.0);
    }
}
