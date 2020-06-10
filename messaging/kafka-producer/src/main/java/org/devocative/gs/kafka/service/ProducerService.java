package org.devocative.gs.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final KafkaTemplate<String, String> kafkaTemplate;

	// ------------------------------

	@Scheduled(initialDelay = 8000, fixedRate = 5000)
	public void produce() {
		final String message = String.format("MSG - %s", SDF.format(new Date()));

		log.info("Produce - [{}]", message);

		kafkaTemplate.send("simple-messaging", message);
	}

}
