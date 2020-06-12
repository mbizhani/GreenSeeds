package org.devocative.gs.stream.service;

import lombok.extern.slf4j.Slf4j;
import org.devocative.gs.stream.model.Message;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
public class Consumer {

	@StreamListener(target = Sink.INPUT)
	public void consumeText(String message) {
		log.info("Stream.ConsumeText: {}", message);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['type'] == 'message'")
	public void consumeObject(Message message) {
		log.info("Stream.ConsumeObject: {}", message);
	}

}
