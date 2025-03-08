package com.ortakciemrecan.courier.repository;

import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourierStoreEntryLogRepository extends JpaRepository<CourierStoreEntryLog, Long> {
        @Query("SELECT SUM(c.totalEntrance) FROM CourierStoreEntryLog c WHERE c.courier.id = :courierId")
        Optional<Double> findTotalEntranceByCourierId(Long courierId);
}
