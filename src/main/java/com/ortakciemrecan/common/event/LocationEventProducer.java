package com.ortakciemrecan.common.event;

import com.ortakciemrecan.courier.dto.CourierLocationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationEventProducer {
    private final KafkaTemplate<String, CourierLocationEvent> kafkaTemplate;
    @Value("${vars.location-events.topic}")
    private String courierLocationEventsTopic;
    public void sendEvent(CourierLocationEvent event) {
        kafkaTemplate.send(courierLocationEventsTopic, event.courierId().toString(), event);
    }
}
