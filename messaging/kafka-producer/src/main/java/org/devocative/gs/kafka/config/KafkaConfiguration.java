package org.devocative.gs.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {

	/*static {
		System.setProperty("spring.kafka.producer.key-serializer", StringSerializer.class.getName());
		System.setProperty("spring.kafka.producer.value-serializer", JsonSerializer.class.getName());
	}*/

	// ------------------------------

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		final Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",",
			Arrays.asList(
				"localhost:9092",
				"localhost:29092")));
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	// ------------------------------

	@Component
	public static class MyKafkaTemplate extends KafkaTemplate<String, Object> {

		MyKafkaTemplate(ProducerFactory<String, Object> producerFactory) {
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
}
