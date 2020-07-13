package org.devocative.gs.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.devocative.gs.kafka.model.MessageReceive;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

	@KafkaListener(topics = "simple-messaging")
	public void consume(MessageReceive message,
						@Header("FOO") String fooHeader,
						@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {

		log.info("Consumer: msg=[{}] - foo=[{}] - key=[{}]", message, fooHeader, key);
	}

}
