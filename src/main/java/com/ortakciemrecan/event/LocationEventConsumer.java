package com.ortakciemrecan.event;

import com.ortakciemrecan.dto.CourierLocationEvent;
import com.ortakciemrecan.dto.Store;
import com.ortakciemrecan.entity.CourierStoreEntryLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.ortakciemrecan.service.CourierStoreEntryLogService;
import com.ortakciemrecan.service.HaversineDistanceCalculator;
import com.ortakciemrecan.service.StoreLoader;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationEventConsumer {
    private final RedisTemplate<String, String> redisTemplate;
    private final StoreLoader storeLoader;
    private final HaversineDistanceCalculator calculator;
    private final CourierStoreEntryLogService logService;

    @KafkaListener(topics = "courier-location-events", groupId = "courier-tracking-group")
    public void consume(CourierLocationEvent event) {
        storeLoader.getStores().stream()
                .filter(store -> isWithinRadius(event, store, 100))
                .forEach(store -> processEntry(event, store));
    }

    private boolean isWithinRadius(CourierLocationEvent event, Store store, double radiusMeters) {
        double distance = calculator.calculate(
                event.getLatitude(), event.getLongitude(),
                store.getLat(), store.getLng()
        );
        return distance <= radiusMeters;
    }

    private void processEntry(CourierLocationEvent event, Store store) {
        String key = generateRedisKey(event.getCourierId(), store.getName());

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.opsForValue().set(key, "entered", 60, TimeUnit.SECONDS);
            CourierStoreEntryLog courierStoreEntryLog = new CourierStoreEntryLog();
            courierStoreEntryLog.setCourierId(event.getCourierId());
            courierStoreEntryLog.setStoreName(store.getName());
            courierStoreEntryLog.setEntryTime(event.getTimestamp());
            logService.save(courierStoreEntryLog);
            log.info("Courier {} entered store {}.", event.getCourierId(), store.getName());
        }
    }

    private String generateRedisKey(UUID courierId, String storeName) {
        return "courier:" + courierId + ":store:" + storeName;
    }
}
