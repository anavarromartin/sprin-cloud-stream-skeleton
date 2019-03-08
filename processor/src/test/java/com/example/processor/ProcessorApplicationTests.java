package com.example.processor;

import lombok.RequiredArgsConstructor;
import org.jboss.logging.LogMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessorApplicationTests {


	@Autowired
	private Processor pipe;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	public void whenMessageIsReceived_itTransformsItToUpperCase() {
		pipe.input()
				.send(MessageBuilder.withPayload("test message")
						.build());

		Object payload = messageCollector.forChannel(pipe.output())
				.poll()
				.getPayload();

		assertEquals("TEST MESSAGE", payload.toString());
	}

}
