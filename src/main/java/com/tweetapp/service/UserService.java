package com.tweetapp.service;

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

	//	public Optional<UserEntity> findUserById(String email) {
	public UserEntity findUserById(String email) {
		return repo.findById(email).orElseThrow(() -> new UserNotFoundException("User not found for id -> " + email));

//		if (user.isEmpty()) {
//			throw new UserNotFoundException("Error");
//		} else {
//			return user;
//		}
//		Throw(() -> new UserNotFoundException("User not found for id -> " + email));

	}


}
