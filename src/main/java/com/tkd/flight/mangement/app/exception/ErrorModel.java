package com.tkd.flight.mangement.app.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String path;
}
