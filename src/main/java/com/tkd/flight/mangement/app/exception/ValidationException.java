package com.tkd.flight.mangement.app.exception;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException {
	private final BindingResult bindingResults;

	public ValidationException(BindingResult bindingResults) {
		super();
		this.bindingResults = bindingResults;
	}

	@Override
	public String getMessage() {
		return bindingResults.getAllErrors().stream().map(t -> {
			if (t instanceof FieldError error) {
				String className = error.getObjectName();
				String field = error.getField();
				Object rejectedValue = error.getRejectedValue();
				String defaultMessage = error.getDefaultMessage();
				return String.format("%s.%s:%s, and it was %s", className, field, defaultMessage, rejectedValue);
			} else {
				return String.format("%s: %s", t.getObjectName(), t.getDefaultMessage());
			}
		}).toList().toString();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7721855973642632771L;

}
