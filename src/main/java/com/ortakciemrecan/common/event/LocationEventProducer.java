package com.ortakciemrecan.common.event;

import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationEventProducer {

    private final KafkaTemplate<String, CourierLocationEvent> kafkaTemplate;
    public void sendEvent(CourierLocationEvent event) {
        kafkaTemplate.send("courier-location-events", event.courierId().toString(), event);
    }
}
