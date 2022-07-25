package com.tweetapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tweetapp.model.util.DateUtil;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.util.Date;

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
//	@Past(message = "Date should be in past")
//	@NotBlank(message = "Date of birth is mandatory")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private Date dateOfBirth;

	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@Column(columnDefinition = "String default 'false'")
	private String loggedIn;

//	@OneToMany(mappedBy = "email", targetEntity = Tweet.class, cascade = CascadeType.ALL)
//	// @JoinColumn(name = "email_id", referencedColumnName = "id")
//	private List<Tweet> tweets;

	public UserEntity(String emailId, String firstName, String lastName, String gender, String dateOfBirth, String password)
			throws ParseException {
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = DateUtil.convertToDate(dateOfBirth);
		this.password = password;
//		this.loggedIn = "false";
	}

	public synchronized String getLoggedIn() {
		return loggedIn;
	}

	public synchronized void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}
}
