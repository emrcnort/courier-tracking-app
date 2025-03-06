package com.ortakciemrecan.controller;

import com.ortakciemrecan.dto.CourierLocationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ortakciemrecan.event.LocationEventProducer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationEventProducer producer;

    @PostMapping
    public ResponseEntity<Void> postLocation(@RequestBody CourierLocationEvent event) {
        producer.sendEvent(event);
        return ResponseEntity.accepted().build();
    }
}
