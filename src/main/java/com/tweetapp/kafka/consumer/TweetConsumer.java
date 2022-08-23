package com.tweetapp.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TweetConsumer {

    @KafkaListener(topics = "tweet-events", groupId = "test-consumer-group")
    public void consume(String message) {
        log.info("message -> {}", message);
        System.out.println("message = " + message);
    }

}
