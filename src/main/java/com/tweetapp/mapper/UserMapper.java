package com.tweetapp.mapper;

import com.tweetapp.model.UserEntity;
import com.tweetapp.model.dto.request.UserRequest;
import com.tweetapp.model.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class UserMapper {

	public static UserEntity mapUser(UserRequest userRequest) throws ParseException {
		return new UserEntity(userRequest.getEmailId(), userRequest.getFirstName(), userRequest.getLastName(),
				userRequest.getGender(), userRequest.getContactNumber(),userRequest.getDateOfBirth(), userRequest.getPassword());
	}

	public static UserResponse toUserResponse(UserEntity user) {
		return new UserResponse(user.getEmailId(), user.getFirstName(), user.getLastName(),
				user.getGender(), user.getContactNumber(), user.getDateOfBirth());
	}
}
