package com.tweetapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {
	private long id;
	private String email;
	private String body;
	private int likes;
	private Date timestamp;
}
