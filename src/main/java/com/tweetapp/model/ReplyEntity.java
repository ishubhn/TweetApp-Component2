package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private long id;
	private String body;
	private String email;
	private int likes = 0;
	private Date date = new Date();

	public ReplyEntity(long id, String email, String body) {
		this.id = id;
		this.email = email;
		this.body = body;
	}
}