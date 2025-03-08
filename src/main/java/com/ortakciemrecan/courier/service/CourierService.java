package com.ortakciemrecan.courier.service;

import com.ortakciemrecan.common.event.LocationEventProducer;
import com.ortakciemrecan.common.exception.CourierNotActiveException;
import com.ortakciemrecan.common.exception.CourierNotExistException;
import com.ortakciemrecan.courier.dto.CourierDistanceDto;
import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.entity.Courier;
import com.ortakciemrecan.courier.repository.CourierRepository;
import com.ortakciemrecan.courier.utils.CourierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository repository;
    private final LocationEventProducer producer;
    private final CourierMapper mapper;
    private final CourierStoreEntryLogService courierStoreEntryLogService;
    public void updateLocation(CourierLocationEvent courierLocationEvent) throws CourierNotActiveException {
        repository.findById(courierLocationEvent.courierId())
                .filter(Courier::getIsActive)
                .orElseThrow(CourierNotActiveException::new);
        producer.sendEvent(courierLocationEvent);
    }

    public Courier getReferenceById(Long courierId) throws CourierNotExistException {
        return Optional.ofNullable(repository.getReferenceById(courierId)).orElseThrow(CourierNotExistException::new);
    }

    @Transactional(readOnly = true)
    public Page<CourierDto> getCouriers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Courier> couriers = repository.findAll(pageable);
        return couriers.map(courier -> mapper.convertEntityToDto(courier));
    }

    @Transactional
    public void saveOrUpdateCourier(CourierRequest courierRequest) {
        repository.findByEmployeeNumber(courierRequest.employeeNumber())
                .ifPresentOrElse(existingCourier -> {
                    existingCourier.setName(courierRequest.name());
                }, () -> {
                    var newCourier = mapper.convertRequestToDto(courierRequest);
                    repository.save(newCourier);
                });
    }

    public CourierDistanceDto getTotalDistance(Long courierId) {
        double totalDistanceAsMeters = courierStoreEntryLogService.getTotalEntranceByCourierId(courierId);
        double totalDistanceAsKilometers = totalDistanceAsMeters / 1000;
        return new CourierDistanceDto(totalDistanceAsKilometers);
    }
}
