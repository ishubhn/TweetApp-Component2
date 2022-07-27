package com.tweetapp.model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tweets")
public class TweetEntity {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(targetEntity = UserEntity.class)
	private String email;
	private String body;
	private int likes;
	private Date timestamp;

	public TweetEntity(String email, String body) {
		this.email = email;
		this.body = body;
		this.likes = 0;
		this.timestamp = new Date();
	}

}
