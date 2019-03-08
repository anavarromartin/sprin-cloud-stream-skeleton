package com.example.source;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class) // default Spring Cloud Stream interface for only sending messages
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledMessage {

    // Instantiation of the interface to connect with the message broker under the abstraction
    // (autowiring enabled by @EnableBinding)
    private final Source source;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("sending message");
        source.output().send(new GenericMessage<>("hello world"));
    }
}
