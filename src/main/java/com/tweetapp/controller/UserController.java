package com.tweetapp.controller;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserEntity;
import com.tweetapp.model.dto.ForgotPasswordRequest;
import com.tweetapp.model.dto.LoginRequest;
import com.tweetapp.model.dto.UserResponse;
import com.tweetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class UserController {
	@Autowired
	UserService service;

	@GetMapping(path = "/users/all")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		return new ResponseEntity<>(service.findAllUsers(), HttpStatus.OK);
	}

	@GetMapping(path = "/user/search/{username}")
	public ResponseEntity<UserEntity> findUserByID(@PathVariable String username) throws UserNotFoundException {
		return new ResponseEntity<>(service.findUserById(username), HttpStatus.OK);
	}

	@PostMapping(path = "/register")
	public ResponseEntity<String> registerUser (@RequestBody UserEntity newUser) {
		return new ResponseEntity<>(service.registerUser(newUser), HttpStatus.CREATED);
	}

	@PostMapping(path = "/login")
	public ResponseEntity<UserResponse> loginUser (@RequestBody LoginRequest request) {
		return new ResponseEntity<>(service.login(request), HttpStatus.OK);
	}

	@PostMapping(path = "/{username}/forgot")
	public ResponseEntity<String> forgotPassword (@PathVariable String username,
	                                              @RequestBody ForgotPasswordRequest request) throws ParseException {
//		return new ResponseEntity<>(service.updatePassword(username, password, newPassword, dateOfBirth), HttpStatus.OK);
		return new ResponseEntity<>(service.updatePassword(username, request.getPassword(),
					request.getNewPassword(), request.getDateOfBirth()), HttpStatus.OK);
	}
}
