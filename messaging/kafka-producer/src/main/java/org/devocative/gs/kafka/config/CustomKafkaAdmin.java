package org.devocative.gs.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomKafkaAdmin extends KafkaAdmin {
	private final GenericApplicationContext applicationContext;

	public CustomKafkaAdmin(GenericApplicationContext applicationContext) {
		super(create());

		this.applicationContext = applicationContext;
	}

	@Override
	public void afterSingletonsInstantiated() {
		//TIP: register bean at runtime
		applicationContext.registerBean("kafka-topic-simple-messaging", NewTopic.class, () -> TopicBuilder.name("simple-messaging")
			.partitions(3)
			//.replicas()
			.build());

		initialize();
	}

	private static Map<String, Object> create() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",",
			Arrays.asList(
				"localhost:9092",
				"localhost:29092")));
		return config;
	}

}
