package com.tkd.flight.mangement.app.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FlightSchedule {
	private Flight flight;
	private Airport source;
	private Airport destination;
	private int duration;
	private Date departureTime;
	private String gate;
	private List<FlightScheduleSeat> flightScheduleSeats;
}
