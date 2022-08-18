package com.tweetapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private String emailId;
	private String firstName;
	private String lastName;
	private String gender;
	private String contactNumber;
	private Date dateOfBirth;
}
