package com.tweetapp.service;

import com.tweetapp.exception.InvalidLoginException;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.UserEntity;
import com.tweetapp.model.dto.LoginRequest;
import com.tweetapp.model.dto.UserResponse;
import com.tweetapp.model.util.DateUtil;
import com.tweetapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tweetapp.mapper.UserMapper.toUserResponse;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repo;

	public List<UserEntity> findAllUsers() {
		return repo.findAll();
	} // ok

	public UserEntity findUserById(String email) {
		return repo.findById(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for id -> " + email));
	}    // ok

	public boolean isUserPresent(String email) {
		UserEntity user = repo.findById(email).orElse(null);
		return user != null;
	} // ok

	public String registerUser(UserEntity user) {

		if (!isUserPresent(user.getEmailId())) {  // user not present already
			repo.save(user);
			log.info("User not found. User created successfully");
		} else {
			log.info("User already present");
			throw new UserAlreadyExistException("User already exists for the user -> " + user.getEmailId());
		}
		return "User Created successfully -> " + user;
	} // ok

	public UserResponse login(LoginRequest request) {
		if (isUserPresent(request.getEmail())) {
			log.info("User present in db");
			UserEntity user = findUserById(request.getEmail());

			if (user.getEmailId().equalsIgnoreCase(request.getEmail())
					&& user.getPassword().equalsIgnoreCase(request.getPassword())) {
				log.info("User credentials matched");

				user.setLoggedIn("TRUE");
				repo.save(user);

				log.info("User logged in successfully -> " + user.getEmailId());
				return toUserResponse(user);

			} else {
				log.info("bad credentials");
				throw new InvalidLoginException("Invalid User Credentials. Please use correct credentials");
			}
		} else {
			log.error("User login unsuccessful");
		}

		return null;
	} // ok

	public String updatePassword(String email, String password, String newPassword, String dateOfBirth)
			{
//	public String updatePassword(String email, String password, String newPassword) {
		if (isUserPresent(email)) {
			UserEntity user = findUserById(email);
			log.info("User present.");

			if (user.getPassword().equals(password)
					&& user.getLoggedIn().equalsIgnoreCase("true")
					&& DateUtil.convertDateToString(user.getDateOfBirth()).equalsIgnoreCase(dateOfBirth))
			{
				if (user.getPassword().equals(newPassword)) {
					log.error("New password must be different from old password");
					throw new InvalidLoginException("New password must be different from old password");
				}
				user.setPassword(newPassword);
				repo.save(user);
				log.info("Password for user ->  " + email + " changed successfully.");
				return "Password for user ->  " + email + " changed successfully.";
			} else {
				log.error("User credentials mismatched. Unable to process request");
				throw new InvalidLoginException("Invalid User Credentials, or user not logged in or invalid date of birth");
			}
		} else {
			log.error("User not found.");
		}
		return "An error occurred while updating password";
	} // ok
}
