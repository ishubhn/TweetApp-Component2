package com.tweetapp.service;

import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserEntity;
import com.tweetapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repo;

	public List<UserEntity> findAllUsers() {
		return repo.findAll();
	}

	public UserEntity findUserById(String email) {
		return repo.findById(email.toLowerCase()).orElseThrow(() -> new UserNotFoundException("User not found for id -> " + email));
	}

	public String registerUser(UserEntity user) {
		// if user is not present -> then create new user
//		if (!isUserPresent(user.getEmailId())) {
			repo.save(user);
			log.info("User not found.");
//		} else {
//			log.info("User already present");
//			throw new UserAlreadyExistException("User already exists for the user -> " + user.getEmailId());
//		}
		return "User Created successfully -> " + user.toString();
	}

//	public boolean isUserPresent(String email) {
//		UserEntity user = findUserById(email);
//		boolean flag =  user != null ? true : false;
//		return flag;
//	}
}
