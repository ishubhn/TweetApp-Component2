package com.tweetapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tweetapp.model.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@Column(name = "email", nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String emailId;

	@Column(name = "first_name")
	@Size(min = 2)
	@NotBlank(message = "First Name is mandatory")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "Gender is mandatory")
	private String gender;

	@Column(name = "date_of_birth")
	@Past(message = "Date should be in past")
	@NotBlank(message = "Date of birth is mandatory")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private Date dateOfBirth;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private String loggedIn = "false";

	// @JoinColumn(name = "email_id", referencedColumnName = "id")
	@OneToMany(mappedBy = "email", targetEntity = TweetEntity.class, cascade = CascadeType.ALL)
	private List<TweetEntity> tweets;

	public UserEntity(String emailId, String firstName, String lastName, String gender, String dateOfBirth, String password)
			throws ParseException {
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = DateUtil.convertToDate(dateOfBirth);
		this.password = password;

	}

	// Getters and Setters
	public synchronized String getLoggedIn() {
		return loggedIn;
	}

	public synchronized void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

}