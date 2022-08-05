package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private long id;
	private String reply;
	private Date date;
	private String email;
}