package org.devocative.gs.kafka.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageSend {
	private String body;
	private Date date;
	private Integer weight;
}
