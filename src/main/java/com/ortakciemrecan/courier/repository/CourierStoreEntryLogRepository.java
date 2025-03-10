package com.ortakciemrecan.courier.repository;

import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierStoreEntryLogRepository extends JpaRepository<CourierStoreEntryLog, Long> {
        Optional<Double> findTotalEntranceByCourierId(Long courierId);
}
