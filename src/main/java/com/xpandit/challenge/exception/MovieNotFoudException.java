package com.xpandit.challenge.exception;

public class MovieNotFoudException extends RuntimeException {
	private static final long serialVersionUID = 7562865219110345474L;
	
	public MovieNotFoudException() {
		super();
	}
	
	public MovieNotFoudException(String message) {
		super(message);
	}

}
