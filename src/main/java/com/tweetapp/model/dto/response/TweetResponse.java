package com.tweetapp.model.dto.response;

import com.tweetapp.model.ReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {
	private long id;
	private String email;
	private String body;
	private int likes;
	private List<ReplyEntity> replies;
	private Date timestamp;
}
