package com.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@EnableBinding(Processor.class)
@Slf4j
public class MessageProcessor {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Flux<String> toUpperCase(Flux<String> messages) {
        return messages
                .doOnNext(message -> log.info("received message: {}", message))
                .map(String::toUpperCase)
                .doOnNext(message -> log.info("sending message: {}", message));
    }
}
