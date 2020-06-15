package org.devocative.gs.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.devocative.gs.kafka.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

	@KafkaListener(topics = "simple-messaging")
	public void consume(Message message) {
		log.info("Consumer - [{}]", message);
	}

}
