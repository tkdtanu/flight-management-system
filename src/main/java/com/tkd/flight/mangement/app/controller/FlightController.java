package com.tkd.flight.mangement.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkd.flight.mangement.app.dto.Flight;
import com.tkd.flight.mangement.app.dto.TravelSearchResult;
import com.tkd.flight.mangement.app.service.FlightService;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class FlightController {

	private final FlightService flightService;

	@GetMapping("/search")
	public TravelSearchResult searchFlightsForGivenInputs(@RequestParam @NotBlank String source,
			@RequestParam @NotBlank String destination,
			@RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd" )@FutureOrPresent Date date) {
		return flightService.searchFlightsForGivenInputs(source, destination, date);
	}

	@GetMapping("/flights/search")
	public List<Flight> searchFlightsByAirline(@RequestParam @NotBlank String airline) {
		return flightService.searchFlightsByAirline(airline);
	}
}
