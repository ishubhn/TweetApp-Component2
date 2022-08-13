package com.tweetapp.controller;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.ReplyEntity;
import com.tweetapp.service.ReplyService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class ReplyController {
	@Autowired
	UserService userService;

	@Autowired
	TweetService tweetService;

	@Autowired
	ReplyService replyService;

	@PostMapping(path = "/{username}/reply/{id}")
	public ResponseEntity<ReplyEntity> postReply(@PathVariable String username, @PathVariable long id,
	                                             @RequestParam String body) {
		isValidTweet(body);
			return new ResponseEntity<>
					(replyService.postReply(new ReplyEntity(id,username,body)), HttpStatus.CREATED);
//		return new ResponseEntity<>
//				(replyService.postReply(new ReplyEntity(username,body,tweetService.findTweetById(id))), HttpStatus.CREATED);
	}

	private void isValidTweet(String tweet) {
		if (isEmpty(tweet)) {
			throw new TweetNotFoundException("Invalid Tweet Content");
		}
	}
}
