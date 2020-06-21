package org.devocative.gs.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfiguration {

	/*static {
		System.setProperty("spring.kafka.consumer.key-deserializer", StringDeserializer.class.getName());
		System.setProperty("spring.kafka.consumer.value-deserializer", JsonDeserializer.class.getName());
		System.setProperty("spring.kafka.consumer.properties.spring.json.trusted.packages", "*");
		System.setProperty("spring.kafka.consumer.auto-offset-reset", "earliest");
	}*/

	@Value("${spring.application.name}")
	private String appName;

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
		jsonDeserializer.addTrustedPackages("*");

		final Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",",
			Arrays.asList(
				"localhost:9092",
				"localhost:29092")));

		//config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//TIP: Expected a Class instance or class name!
		//config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);

		config.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, "false");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, appName);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
	}

	/*
	TIP: this bean name must be 'kafkaListenerContainerFactory' (as method name) to override boot default bean in its 'KafkaAnnotationDrivenConfiguration' class.
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
		final ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}


}
