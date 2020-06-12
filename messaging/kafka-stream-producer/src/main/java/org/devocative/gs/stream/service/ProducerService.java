package org.devocative.gs.stream.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devocative.gs.stream.model.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final Producer producer;

	// ------------------------------

	@Scheduled(initialDelay = 8000, fixedRate = 5500)
	public void produceText() {
		final String message = String.format("STREAM - %s", SDF.format(new Date()));

		log.info("Stream.ProducerText: {}", message);

		producer.getSource()
			.output()
			.send(MessageBuilder.withPayload(message)
				.setHeader("type", "string")
				.build());
	}

	@Scheduled(initialDelay = 8000, fixedRate = 6500)
	public void produceObject() {
		final Message msg = new Message(UUID.randomUUID().toString(), new Date(), (int) (Math.random() * 10) + 1);

		log.info("Stream.ProducerObject: {}", msg);

		producer.getSource()
			.output()
			.send(MessageBuilder.withPayload(msg)
				.setHeader("type", "message")
				.build());
	}

}
