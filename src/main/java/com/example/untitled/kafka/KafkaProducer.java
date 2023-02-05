package com.example.untitled.kafka;

import com.example.untitled.dto.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final StreamBridge streamBridge;

    public void sendMessage(Hello hello) {
        streamBridge.send("basicProducer-out-0", hello);
    }



}
