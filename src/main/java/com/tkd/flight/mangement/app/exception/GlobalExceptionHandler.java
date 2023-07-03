package com.tkd.flight.mangement.app.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorModel> validationException(ValidationException exception,
			HttpServletRequest servletRequest) {
		return ResponseEntity.badRequest()
				.body(buildErrorModel(exception.getMessage(), servletRequest, BAD_REQUEST));
	}

	@ExceptionHandler(InvalidAirlineException.class)
	public ResponseEntity<ErrorModel> invalidAirlineException(InvalidAirlineException exception,
			HttpServletRequest servletRequest) {
		return ResponseEntity.badRequest()
				.body(buildErrorModel(exception.getMessage(), servletRequest, BAD_REQUEST));
	}
	
	@ExceptionHandler(InvalidAirportException.class)
	public ResponseEntity<ErrorModel> invalidAirportException(InvalidAirportException exception,
			HttpServletRequest servletRequest) {
		return ResponseEntity.badRequest()
				.body(buildErrorModel(exception.getMessage(), servletRequest, BAD_REQUEST));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorModel> formattingException(HttpMessageNotReadableException exception,
			HttpServletRequest servletRequest) {
		return ResponseEntity.badRequest().body(buildErrorModel(exception.getMessage(), servletRequest, BAD_REQUEST));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorModel> methodArgumentException(MethodArgumentNotValidException exception,
			HttpServletRequest servletRequest) {
		return ResponseEntity.badRequest()
				.body(buildErrorModel(extractValidationMessage(exception), servletRequest, BAD_REQUEST));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorModel> commonException(Exception exception, HttpServletRequest servletRequest) {
		return ResponseEntity.internalServerError()
				.body(buildErrorModel(exception.getMessage(), servletRequest, INTERNAL_SERVER_ERROR));
	}

	private ErrorModel buildErrorModel(String message, HttpServletRequest servletRequest, HttpStatus status) {
		return ErrorModel.builder().message(message).path(servletRequest.getRequestURI()).status(status.value())
				.timestamp(LocalDateTime.now()).build();
	}

	private String extractValidationMessage(MethodArgumentNotValidException exception) {
		return exception.getAllErrors().stream().map(t -> {
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
}
