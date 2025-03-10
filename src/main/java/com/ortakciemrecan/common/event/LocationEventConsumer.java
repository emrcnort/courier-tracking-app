package com.ortakciemrecan.common.event;

import com.ortakciemrecan.common.exception.CourierNotExistException;
import com.ortakciemrecan.common.exception.StoreNotExistException;
import com.ortakciemrecan.common.service.HaversineDistanceCalculator;
import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import com.ortakciemrecan.courier.entity.Courier;
import com.ortakciemrecan.courier.entity.CourierStoreEntryLog;
import com.ortakciemrecan.courier.service.CourierService;
import com.ortakciemrecan.courier.service.CourierStoreEntryLogService;
import com.ortakciemrecan.store.dto.StoreDto;
import com.ortakciemrecan.store.entity.Store;
import com.ortakciemrecan.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationEventConsumer {
    private final RedisTemplate<String, String> redisTemplate;
    private final HaversineDistanceCalculator calculator;
    private final CourierStoreEntryLogService logService;
    private final StoreService storeService;
    private final CourierService courierService;

    @KafkaListener(topics = "${vars.location-events.topic}", groupId = "${vars.location-events.group-id}")
    public void consume(CourierLocationEvent event) {
        storeService.getStores().stream()
                .filter(storeDto -> isWithinRadius(event, storeDto, 100))
                .forEach(storeDto -> {
                    try {
                        processEntry(event, storeDto);
                    } catch (CourierNotExistException | StoreNotExistException e) {
                        log.error(e.getLocalizedMessage());
                    }
                });
    }

    private boolean isWithinRadius(CourierLocationEvent event, StoreDto storeDto, double radiusMeters) {
        double distance = calculator.calculate(
                event.latitude(), event.longitude(),
                storeDto.getLatitude(), storeDto.getLongitude()
        );
        return distance <= radiusMeters;
    }

    private void processEntry(CourierLocationEvent event, StoreDto storeDto) throws CourierNotExistException, StoreNotExistException {
        String key = generateRedisKey(event.courierId(), storeDto.getName());

        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            Courier courier = fetchCourier(event.courierId());
            Store store = fetchStore(storeDto.getName());
            double totalEntrance = logService.getTotalEntranceByCourierId(courier.getId());

            markAsEntered(key);
            logEntry(event.courierId(), storeDto.getName(), courier, store, totalEntrance);
        }
    }

    private Courier fetchCourier(Long courierId) throws CourierNotExistException {
        return courierService.getReferenceById(courierId);
    }

    private Store fetchStore(String storeName) throws StoreNotExistException {
        return storeService.getStoreByName(storeName);
    }

    private void markAsEntered(String key) {
        redisTemplate.opsForValue().set(key, "entered", 60, TimeUnit.SECONDS);
    }

    private void logEntry(Long courierId, String storeName, Courier courier, Store store, double totalEntrance) {
        CourierStoreEntryLog courierStoreEntryLog = createLogEntry(courier, store, totalEntrance);
        logService.save(courierStoreEntryLog);
        log.info("Courier {} entered store {}.", courierId, storeName);
    }

    private CourierStoreEntryLog createLogEntry(Courier courier, Store store, double totalEntrance) {
        CourierStoreEntryLog courierStoreEntryLog = new CourierStoreEntryLog();
        courierStoreEntryLog.setCourier(courier);
        courierStoreEntryLog.setStore(store);
        courierStoreEntryLog.setEntranceTime(LocalDateTime.now());
        courierStoreEntryLog.setTotalEntrance(totalEntrance);
        return courierStoreEntryLog;
    }

    private String generateRedisKey(Long courierId, String storeName) {
        return String.format("courier:%d:store:%s", courierId, storeName);
    }
}
