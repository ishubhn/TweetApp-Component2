package com.tweetapp.service;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.TweetEntity;
import com.tweetapp.model.UserEntity;
import com.tweetapp.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

	@Autowired
	private TweetRepository repo;

	public List<TweetEntity> findAllTweets() {
		return repo.findAll();
	}

	public TweetEntity findTweetByEmail(String email) {
		return (TweetEntity) repo.findById(email).orElseThrow(() -> new UserNotFoundException("User not found for id -> " + email));
	}

	public String registerTweet(TweetEntity tweet) {
		repo.save(tweet);
		return "Tweet registered";
	}

}
