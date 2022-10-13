package com.xpandit.challenge.exceptionhandler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xpandit.challenge.exception.MovieAlreadyExistsException;
import com.xpandit.challenge.exception.MovieNotFoudException;

@RestControllerAdvice
public class MovieExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handleMovieNotFoudException(MovieNotFoudException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleMovieAlreadyExistsException(MovieAlreadyExistsException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return new ResponseEntity<>(buildMessage(exception.getFieldErrors()), HttpStatus.BAD_REQUEST);
	}
	
	private String buildMessage(List<FieldError> errorList) {
		var message = new StringBuilder();
		for (FieldError fieldError : errorList) {
			message.append(fieldError.getField() + " " + fieldError.getDefaultMessage() + ", ");
		}
		message.setLength(message.length()-2);
		return message.toString();
	}
	
	@ExceptionHandler
	private ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(exception.getMostSpecificCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
}
