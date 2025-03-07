package com.ortakciemrecan.courier.controller;

import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/couriers")
public class CourierController {
    private final CourierService courierService;

    @PostMapping("/location")
    public ResponseEntity<Void> updateLocation(@RequestBody CourierLocationEvent courierLocationEvent) {
        courierService.updateLocation(courierLocationEvent);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<CourierDto> getCouriers(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "30") int size) {
        return courierService.getCouriers(page, size);
    }

    @PostMapping
    public ResponseEntity<Void> saveCourier(@RequestBody CourierRequest courierRequest) {
        courierService.saveOrUpdateCourier(courierRequest);
        return ResponseEntity.ok().build();
    }

}
