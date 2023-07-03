package com.tkd.flight.mangement.app.exception;

public class InvalidAirportException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808695516171885789L;

	public InvalidAirportException() {
		super();
	}

	public InvalidAirportException(String message) {
		super(message);
	}

}
