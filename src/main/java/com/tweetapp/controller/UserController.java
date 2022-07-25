package com.tweetapp.controller;

import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserEntity;
import com.tweetapp.service.UserService;
import org.hibernate.event.service.spi.JpaBootstrapSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class UserController {
	@Autowired
	UserService service;

	@GetMapping(path = "/hello")
	public String helloWorld() {
		return "Hello World";
	}

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

}
