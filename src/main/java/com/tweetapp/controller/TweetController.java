package com.tweetapp.controller;

import com.tweetapp.model.TweetEntity;
import com.tweetapp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class TweetController {
	@Autowired
	TweetService service;

	@GetMapping(path = "/all")
	public ResponseEntity<List<TweetEntity>> findAllTweets() {
		return new ResponseEntity<>(service.findAllTweets(), HttpStatus.OK);
	}

	@PostMapping(path = "/{username}/add")
	public ResponseEntity<String> createTweet(@PathVariable String username, @RequestBody TweetEntity tweetEntity) {
		tweetEntity.setEmail(username);
		return new ResponseEntity<>(service.createTweet(tweetEntity), HttpStatus.OK);
	}
}
