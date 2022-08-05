package com.tweetapp.service;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.TweetEntity;
import com.tweetapp.repository.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

	@Autowired
	private TweetRepository repo;

	private static final Logger log = LoggerFactory.getLogger(TweetService.class);

	public List<TweetEntity> findAllTweets() {
		return repo.findAll();
	}

	public TweetEntity findTweetByEmail(String email) {
		return (TweetEntity) repo.findById(email)
				.orElseThrow(() -> new TweetNotFoundException("Tweet not found for id -> " + email));
	}

	public String createTweet(TweetEntity tweet) {
		repo.save(tweet);
		return "Tweet registered";
	}

}
