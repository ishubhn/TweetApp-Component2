package com.tweetapp.exception;

import org.apache.tomcat.jni.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
	// Specific Exceptions
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException
	(UserNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<?> handleUserAlreadyExistException
			(UserAlreadyExistException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<?> handleInvalidLoginException
			(InvalidLoginException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

//	public ResponseEntity<?> handleTransactionSystemException
//			(TransactionSystemException exception, WebRequest request) {
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Check either date format, or password format",
//				request.getDescription(false));
//
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	// Global Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException
	(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(true));

		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
