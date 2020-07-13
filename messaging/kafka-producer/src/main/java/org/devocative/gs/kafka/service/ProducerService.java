package org.devocative.gs.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devocative.gs.kafka.model.MessageSend;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	// ------------------------------

	@PostConstruct
	public void init() {
		log.info("KafkaTemplate Class = [{}]", kafkaTemplate.getClass());
	}

	@Scheduled(initialDelay = 2000, fixedRate = 3000)
	public void produce() {
		final MessageSend msg = new MessageSend(UUID.randomUUID().toString(), new Date(), (int) (Math.random() * 3) + 1);

		log.info("Producer.Object: {}", msg);

		kafkaTemplate.send("simple-messaging", msg.getWeight().toString(), msg);
	}

}
