package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.jsf.FacesContextUtils;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "replies")
public class ReplyEntity {
	@Id
	@GeneratedValue
	@Column(name = "reply_id")
	private long replyId;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "id", nullable = true)
//	private TweetEntity tweet;
	@Column(nullable = true)
	private long id;
	private String body;
	private String email;
	private int likes = 0;
	private Date date = new Date();

//	public ReplyEntity(String email, String body, TweetEntity tweet) {
//		this.tweet = tweet;
	public ReplyEntity(long id, String email, String body) {
		this.id = id;
		this.email = email;
		this.body = body;
	}
}