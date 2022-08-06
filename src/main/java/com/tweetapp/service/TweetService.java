package com.tweetapp.service;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.mapper.TweetMapper;
import com.tweetapp.model.TweetEntity;
import com.tweetapp.model.dto.response.TweetResponse;
import com.tweetapp.repository.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tweetapp.mapper.TweetMapper.toTweetResponse;

@Service
public class TweetService {

	private static final Logger log = LoggerFactory.getLogger(TweetService.class);

	@Autowired
	private TweetRepository repo;
	@Autowired
	private UserService userService;

	public List<TweetEntity> findAllTweets() {
		List<TweetEntity> tweets = repo.findAll();
		if (tweets.isEmpty()) {
			log.error("No tweet present at this moment");
			throw new TweetNotFoundException("No tweet present at this moment");
		}
		return tweets;
	}

	public List<TweetResponse> getTweetsByUser(String email) {
		return userService.findUserById(email)
				.getTweets()
				.stream()
				.map(TweetMapper::toTweetResponse)
				.collect(Collectors.toList());
	}

	public TweetEntity findTweetsByUserAndId(String email, long id) {
		return repo.findByEmailAndId(email, id);
	}

	/*	public TweetResponse getTweetById() {
	//		return null;
		}
	*/
	// Add validation for Login status (if true), check if user exists
	public String createTweet(TweetEntity tweet) {
		repo.save(tweet);
		return "Tweet registered";
	}

	public TweetResponse updateTweet(long id, String body) {
		// Optional<TweetEntity> tweet = repo.findTweetById(id);
		TweetEntity tweet = repo.findTweetById(id).orElse(null);
		if (tweet != null) {
			tweet.setBody(body);
			repo.save(tweet);
			log.info("tweet id -> " + tweet.getId() + " updated successfully");
			return toTweetResponse(tweet);
		} else {
			return null;
		}
	}

	public TweetEntity likeTweet(String email, long id) {
		TweetEntity tweet = findTweetsByUserAndId(email, id);
		tweet.setLikes(tweet.getLikes() + 1);
		repo.save(tweet);
		return tweet;
	}
}