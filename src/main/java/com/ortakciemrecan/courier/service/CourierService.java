package com.ortakciemrecan.courier.service;

import com.ortakciemrecan.common.event.LocationEventProducer;
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

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository repository;
    private final LocationEventProducer producer;
    private final CourierMapper mapper;
    public void updateLocation(CourierLocationEvent courierLocationEvent) {
        producer.sendEvent(courierLocationEvent);
    }

    public Courier getReferenceById(Long courierId) {
        return repository.getReferenceById(courierId);
    }

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

}
