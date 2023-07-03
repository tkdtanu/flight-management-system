package com.tkd.flight.mangement.app.dto;

import com.tkd.flight.mangement.app.dto.enums.SeatClass;

import lombok.Data;

@Data
public class Seat {
	private String seatNumber;
	private SeatClass seatClass;
}
