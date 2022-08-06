package com.tweetapp.service;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.ReplyEntity;
import com.tweetapp.model.TweetEntity;
import com.tweetapp.repository.ReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {
	private static final Logger log = LoggerFactory.getLogger(ReplyService.class);
	@Autowired
	TweetService tweetService;
	@Autowired
	private ReplyRepository repo;

	public List<ReplyEntity> getRepliesByTweetId(long tweetId) {
		return tweetService
				.findTweetById(tweetId)
				.getReplies()
				.stream()
				.collect(Collectors.toList());
	}

	public ReplyEntity postReply(ReplyEntity reply) {
		TweetEntity tweet = tweetService.findTweetById(reply.getId());

		if (tweet != null) {
			log.info("Reply created -> " + reply.toString());
			repo.save(reply);
			return reply;
		} else {
			throw new TweetNotFoundException("Tweet Not found to register reply");
		}
	}

	// add likes for reply

}
