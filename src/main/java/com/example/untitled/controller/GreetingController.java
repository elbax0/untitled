package com.example.untitled.controller;

import com.example.untitled.dto.Hello;
import com.example.untitled.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequiredArgsConstructor
public class GreetingController {

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000);
//        return new Greeting("hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat")
    public String hello(String message, @DestinationVariable("roomId") int roomId) {
        System.out.println(roomId);
        System.out.println(message);
        return message;
    }

    @ResponseBody
    @GetMapping("/send")
    public String sendMessage() {
        kafkaProducer.sendMessage(new Hello("hi"));
        return "send";
    }


}
