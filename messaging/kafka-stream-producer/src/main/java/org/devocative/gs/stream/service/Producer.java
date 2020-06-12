package org.devocative.gs.stream.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@Slf4j
@Getter
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class Producer {

	private final Source source;

}
