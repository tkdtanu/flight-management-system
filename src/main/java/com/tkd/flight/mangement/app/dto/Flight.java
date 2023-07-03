package com.tkd.flight.mangement.app.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class Flight {
	private UUID id;
	private String flightNumber;
	private String airlineName;

}
