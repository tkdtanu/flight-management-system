package com.tkd.flight.mangement.app.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScheduleFlight extends Flight {
	private int duration;
	private String gate;
	private LocalDateTime departureTime;

}
