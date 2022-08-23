package com.tweetapp.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TweetProducer {
	private static final Logger LOG = LoggerFactory.getLogger(TweetProducer.class);
	private static final String TOPIC = "tweet-events";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public TweetProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String msg) {
//		LOG.info("Message sent -> " + msg);
		this.kafkaTemplate.send(TOPIC, msg);

	}
}
