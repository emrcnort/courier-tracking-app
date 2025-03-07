package com.ortakciemrecan.courier.repository;

import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourierStoreEntryLogRepository extends JpaRepository<CourierStoreEntryLog, Long> {
        @Query("SELECT CAST(c.totalEntrance AS int) FROM CourierStoreEntryLog c WHERE c.courier.id = :courierId AND c.store.name = :storeName")
        Optional<Integer> findTotalEntranceByCourierIdAndStoreName(Long courierId, String storeName);
}
