package com.tkd.flight.mangement.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class Airline {
	private String name;
	private String code;
	private List<Flight> flights;
}
