package com.example.untitled.kafka;

import com.example.untitled.dto.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    @Bean
    public Consumer<Hello> basicConsumer() {
        return hello -> {
            System.out.printf("[%s] : consume, %s\n", LocalDateTime.now(), hello);
            template.convertAndSend("/topic/chat", hello);
        };
    }
}
