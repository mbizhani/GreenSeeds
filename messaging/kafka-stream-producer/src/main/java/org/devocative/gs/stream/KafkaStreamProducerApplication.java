package org.devocative.gs.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamProducerApplication.class, args);
	}

}
