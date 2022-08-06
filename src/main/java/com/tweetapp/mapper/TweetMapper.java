package com.tweetapp.mapper;

import com.tweetapp.model.TweetEntity;
import com.tweetapp.model.dto.response.TweetResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TweetMapper {
	public static TweetResponse toTweetResponse(TweetEntity tweet) {
		return new TweetResponse(tweet.getId(), tweet.getEmail(), tweet.getBody(),
				tweet.getLikes(), tweet.getTimestamp());
	}
}