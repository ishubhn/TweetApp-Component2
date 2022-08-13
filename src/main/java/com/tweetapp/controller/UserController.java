package com.tweetapp.controller;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserEntity;
import com.tweetapp.model.dto.request.ForgotPasswordRequest;
import com.tweetapp.model.dto.request.LoginRequest;
import com.tweetapp.model.dto.response.UserResponse;
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
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return new ResponseEntity<>(service.getUserResponseList(), HttpStatus.OK);
	}

	@GetMapping(path = "/user/search/{username}")
	public ResponseEntity<UserResponse> findUserByID(@PathVariable String username) throws UserNotFoundException {
		return new ResponseEntity<>(service.getUserResponseByUserName(username), HttpStatus.OK);
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
		return new ResponseEntity<>(service.updatePassword(username, request.getPassword(),
					request.getNewPassword(), request.getDateOfBirth()), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{username}/delete")
	public ResponseEntity<String> deleteUser (@PathVariable String username) {
		return new ResponseEntity<>(service.deleteUser(username), HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/{username}/logout")
	public ResponseEntity<String> logout (@PathVariable String username) {
		return new ResponseEntity<>(service.logout(username), HttpStatus.OK);
	}
}
