package org.devocative.gs.stream.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaderMapper;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

@Slf4j
@Configuration
public class KafkaConfiguration {

	static {
		log.info("KafkaConfiguration - static!");
		System.setProperty("spring.cloud.stream.kafka.binder.header-mapper-bean-name", "kafkaHeaderMapper123");
	}

	@Bean
	public KafkaHeaderMapper kafkaHeaderMapper123() {
		return new DefaultKafkaHeaderMapper() {
			@Override
			public void fromHeaders(MessageHeaders messageHeaders, Headers headers) {
				log.info("KafkaCon	figuration.fromHeaders");

				headers.add("FOO", "BAR".getBytes());
				super.fromHeaders(messageHeaders, headers);
			}

			@Override
			public void toHeaders(Headers headers, Map<String, Object> map) {
				log.info("KafkaConfiguration.toHeaders");

				super.toHeaders(headers, map);
			}
		};
	}

}
