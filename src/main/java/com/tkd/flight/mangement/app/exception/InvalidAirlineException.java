package com.tkd.flight.mangement.app.exception;

public class InvalidAirlineException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4354215433109861293L;

	public InvalidAirlineException() {
		super();
	}

	public InvalidAirlineException(String message) {
		super(message);
	}

}
