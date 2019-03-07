package com.example.sink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
@Slf4j
public class MessageLogger {

    @StreamListener(Sink.INPUT)
    public void consumeMessage(String message) {
        log.info(message);
    }
}
