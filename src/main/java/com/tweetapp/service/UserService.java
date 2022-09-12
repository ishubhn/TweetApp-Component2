package com.tweetapp.service;

import com.tweetapp.exception.InvalidLoginException;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.mapper.UserMapper;
import com.tweetapp.model.UserEntity;
import com.tweetapp.model.dto.request.LoginRequest;
import com.tweetapp.model.dto.response.MessageResponse;
import com.tweetapp.model.dto.response.UserResponse;
import com.tweetapp.model.util.DateUtil;
import com.tweetapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tweetapp.mapper.UserMapper.toUserResponse;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository repo;

	public List<UserEntity> findAllUsers() {
		return repo.findAll();
	} // ok

	public List<UserResponse> getUserResponseList() {
		return repo
				.findAll()
				.stream()
				.map(UserMapper::toUserResponse)
				.collect(Collectors.toList());
	} // ok

	public UserEntity findUserById(String email) {
		return repo.findById(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for id -> " + email));
	}    // ok

	public UserResponse getUserResponseByUserName(String username) {
		return toUserResponse(findUserById(username));
	}

	// check if username available
	// check if is user logged in
	public boolean isUserLoggedIn(String email) {
		boolean flag = false;
		UserEntity user = repo.findById(email).orElse(null);

		if (user != null) {
			if (user.getLoggedIn().equalsIgnoreCase("true")) {
				flag = true;
			} else {
				throw new InvalidLoginException("User not logged in -> " + email);
			}
		}

		return flag;
	}

	public boolean isUserPresent(String email) {
		UserEntity user = repo.findById(email).orElse(null);
		return user != null;
	} // ok

	public UserResponse registerUser(UserEntity user) {
		if (!isUserPresent(user.getEmailId())) {  // user not present already
			repo.save(user);
			log.info("User not found. User created successfully");
			log.info("User Created successfully -> " + user);
		} else {
			log.info("User already present");
			throw new UserAlreadyExistException("User already exists for the user -> "
					+ user.getEmailId());
		}
		// return "User Created successfully -> " + user;
		return toUserResponse(user);
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
			log.error("User login unsuccessful. User doesn't exist");
			throw new InvalidLoginException("Invalid User Credentials. Please use correct credentials");
		}
	} // ok

	public MessageResponse updatePassword(String email, String password, String newPassword, String dateOfBirth) {

		if (isUserPresent(email)) {
			UserEntity user = findUserById(email);
			log.info("User present.");

			if (user.getPassword().equals(password)
					&& user.getLoggedIn().equalsIgnoreCase("true")
					&& DateUtil.convertDateToString(user.getDateOfBirth()).equalsIgnoreCase(dateOfBirth)) {
				if (user.getPassword().equals(newPassword)) {
					log.error("New password must be different from old password");
					throw new InvalidLoginException("New password must be different from old password");
				}
				user.setPassword(newPassword);
				repo.save(user);
				log.info("Password for user ->  " + email + " changed successfully.");
				String message = "Password for user ->  " + email + " changed successfully.";
				return new MessageResponse(message, "Success");
			} else {
				log.error("User credentials mismatched. Unable to process request");
				throw new InvalidLoginException("Invalid User Credentials, or user not logged in or invalid date of birth");
			}
		} else {
			log.error("User not found.");
			throw new InvalidLoginException("Invalid User Credentials, or user not logged in or invalid date of birth");
		}
	} // ok

	public MessageResponse deleteUser(String email) {
		if (isUserPresent(email)) {
			UserEntity user = findUserById(email);

			if (user.getLoggedIn().equalsIgnoreCase("true")) {
				repo.delete(user);
				log.info("User deleted successfully");
				String message = "User deleted successfully";
				return new MessageResponse(message, "Success");
			} else {
				throw new InvalidLoginException("User is not logged in");
			}
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	public MessageResponse logout(String email) {
		if (isUserPresent(email)) {
			UserEntity user = findUserById(email);

			if (user.getLoggedIn().equalsIgnoreCase("true")) {
				user.setLoggedIn("false");
				repo.save(user);
				log.info("User logged out successfully");
				String message = "User logged out successfully";
				return new MessageResponse(message, "Success");
			} else {
				throw new InvalidLoginException("User is not logged in");
			}
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

}
