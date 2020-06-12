package org.devocative.gs.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {

	@Bean
	public KafkaTemplate kafkaTemplate(ProducerFactory<?, ?> producerFactory) {
		return new MyKafkaTemplate<>(producerFactory);
	}

	public static class MyKafkaTemplate<K, V> extends KafkaTemplate<K, V> {

		MyKafkaTemplate(ProducerFactory<K, V> producerFactory) {
			super(producerFactory);
		}

		@Override
		protected ListenableFuture<SendResult<K, V>> doSend(ProducerRecord<K, V> producerRecord) {
			producerRecord.headers().add("FOO", "BAR".getBytes());
			return super.doSend(producerRecord);
		}
	}
}
