package com.tweetapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	private String emailId;
	private String firstName;
	private String lastName;
	private String gender;
	private String contactNumber;
	private String dateOfBirth;
	private String password;
}
