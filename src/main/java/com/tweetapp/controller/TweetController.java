package com.tweetapp.controller;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.kafka.producer.TweetProducer;
import com.tweetapp.model.TweetEntity;
import com.tweetapp.model.dto.response.TweetResponse;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class TweetController {
	@Autowired
	TweetService service;

	@Autowired
	UserService userService;

	@Autowired
	TweetProducer tweetProducer;

	@GetMapping(path = "/all")
	public ResponseEntity<List<TweetEntity>> findAllTweets() {
		tweetProducer.sendMessage("Find All tweets");
		return new ResponseEntity<>(service.findAllTweets(), HttpStatus.OK);
	}

	@GetMapping(path = "/{userName}")
	public ResponseEntity<List<TweetResponse>> findTweetsByEmailId(@PathVariable String userName) {
		tweetProducer.sendMessage("Find tweets by user request -> " + userName);
		return new ResponseEntity<>(service.getTweetsByUser(userName), HttpStatus.OK);
	}

	@PostMapping(path = "/{username}/add")
	public ResponseEntity<TweetResponse> postTweet
			(@PathVariable String username, @RequestParam String body) {
		userService.isUserLoggedIn(username);
		isValidTweet(body);
		tweetProducer.sendMessage("Post Tweet request initiatiated");
		return new ResponseEntity<>(service.createTweet(new TweetEntity(username, body)),
				HttpStatus.CREATED);
	}

	@PutMapping(path = "/{username}/update/{id}")
	public ResponseEntity<TweetResponse> updateTweet (@PathVariable String username,
	                                                  @PathVariable long id,
	                                                  @RequestParam String body) {
		userService.isUserLoggedIn(username);
		isValidTweet(body);
		tweetProducer.sendMessage("Update tweet request initiatiated for tweet id -> " + id);
		return new ResponseEntity<>(service.updateTweet(id, body), HttpStatus.OK);
	}

	@PutMapping(path = "/{username}/like/{id}")
	public ResponseEntity<TweetEntity> likeTweet(@PathVariable String username, @PathVariable long id) {
//		userService.isUserLoggedIn(username); //can be used while editing
		validateTweetId(id);
		tweetProducer.sendMessage("liked tweet -> " + id);
		return new ResponseEntity<>(service.likeTweet(username, id), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{username}/delete/{id}")
	public ResponseEntity<String> deleteTweet(@PathVariable String username, @PathVariable long id) {
		userService.isUserLoggedIn(username);
		tweetProducer.sendMessage("Tweet deleted -> " + username);
		return new ResponseEntity<>(service.deleteTweet(username,id),HttpStatus.NO_CONTENT);
	}

	private void validateTweetId(long tweetId) {
		if (tweetId == 0) {
			throw new TweetNotFoundException("Tweet not found");
		}
	}

	private void isValidTweet(String tweet) {
		if (isEmpty(tweet)) {
			throw new TweetNotFoundException("Invalid Tweet Content");
		}
	}
}
