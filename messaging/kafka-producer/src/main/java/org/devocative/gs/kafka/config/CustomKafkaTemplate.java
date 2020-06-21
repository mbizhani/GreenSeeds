package org.devocative.gs.kafka.config;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class CustomKafkaTemplate extends KafkaTemplate<String, Object> {

	CustomKafkaTemplate(ProducerFactory<String, Object> producerFactory) {
		super(producerFactory);
	}

	@Override
	protected ListenableFuture<SendResult<String, Object>> doSend(ProducerRecord<String, Object> producerRecord) {
		if (!producerRecord.topic().equals("simple-messaging")) {
			throw new RuntimeException("Invalid Topic: simple-messaging");
		}

		producerRecord.headers().add("FOO", "BAR".getBytes());
		return super.doSend(producerRecord);
	}
}
