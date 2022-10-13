package com.xpandit.challenge.exception;

public class MovieAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = -5565097468619950618L;

	public MovieAlreadyExistsException() {
		super();
	}

	public MovieAlreadyExistsException(String message) {
		super(message);
	}
	
	

}
