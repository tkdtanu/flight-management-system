package com.tkd.flight.mangement.app.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TravelSearchResult {

	private String source;
	private String destination;
	private List<ScheduleFlight> flights = new ArrayList<>();
}
