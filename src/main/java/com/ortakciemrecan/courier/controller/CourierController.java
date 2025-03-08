package com.ortakciemrecan.courier.controller;

import com.ortakciemrecan.common.exception.CourierNotActiveException;
import com.ortakciemrecan.courier.dto.CourierDistanceDto;
import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/couriers")
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/total-distances/{courier-id}")
    public ResponseEntity<CourierDistanceDto> totalDistance(@PathVariable("courier-id") Long courierId) {
        return new ResponseEntity<>(courierService.getTotalDistance(courierId), HttpStatus.OK);
    }

    @PostMapping("/location")
    public ResponseEntity<Void> updateLocation(@RequestBody CourierLocationEvent courierLocationEvent) throws CourierNotActiveException {
        courierService.updateLocation(courierLocationEvent);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<CourierDto> getCouriers(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) {
        return courierService.getCouriers(page, size);
    }

    @PostMapping
    public ResponseEntity<Void> saveCourier(@RequestBody CourierRequest courierRequest) {
        courierService.saveOrUpdateCourier(courierRequest);
        return ResponseEntity.ok().build();
    }

}
