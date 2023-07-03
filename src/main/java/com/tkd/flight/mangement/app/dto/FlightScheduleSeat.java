package com.tkd.flight.mangement.app.dto;

import com.tkd.flight.mangement.app.dto.enums.BookingStatus;

import lombok.Data;

@Data
public class FlightScheduleSeat extends Seat {

	private int fare;
	private BookingStatus bookingStatus;
}
