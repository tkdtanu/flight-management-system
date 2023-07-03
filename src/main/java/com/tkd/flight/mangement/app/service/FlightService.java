package com.tkd.flight.mangement.app.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.tkd.flight.mangement.app.dto.Flight;
import com.tkd.flight.mangement.app.dto.ScheduleFlight;
import com.tkd.flight.mangement.app.dto.TravelSearchResult;
import com.tkd.flight.mangement.app.entity.Airline;
import com.tkd.flight.mangement.app.entity.Airport;
import com.tkd.flight.mangement.app.entity.FlightSchedule;
import com.tkd.flight.mangement.app.exception.InvalidAirlineException;
import com.tkd.flight.mangement.app.exception.InvalidAirportException;
import com.tkd.flight.mangement.app.repository.AirlineRepository;
import com.tkd.flight.mangement.app.repository.AirportRepository;
import com.tkd.flight.mangement.app.repository.FlightRepository;
import com.tkd.flight.mangement.app.repository.FlightScheduleRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {

	private final FlightScheduleRepository flightScheduleRepository;
	private final FlightRepository flightRepository;
	private final AirlineRepository airlineRepository;
	private final AirportRepository airportRepository;

	public TravelSearchResult searchFlightsForGivenInputs(String source, String destination, Date date) {
		List<Airport> airports = airportRepository.findAll();

		validateSourceAndDestination(source, destination, airports);

		TravelSearchResult travelSearchResult = new TravelSearchResult();
		travelSearchResult.setSource(source);
		travelSearchResult.setDestination(destination);

		List<FlightSchedule> scheduledFlights = flightScheduleRepository
				.findBySourceCodeAndDestinationCodeAndDepartureDate(source, destination,
						LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		if (CollectionUtils.isNotEmpty(scheduledFlights)) {
			Map<UUID, Airline> airlinesMap = airlineRepository.findAll().stream()
					.collect(Collectors.toMap(t -> t.getId(), t -> t));
			List<UUID> flightIds = scheduledFlights.stream().map(t -> t.getFlight().getId()).toList();

			Map<UUID, com.tkd.flight.mangement.app.entity.Flight> flightsMap = getFlightMapById(flightIds);

			travelSearchResult.setFlights(scheduledFlights.stream().map(dbScheduleFlight -> {
				com.tkd.flight.mangement.app.entity.Flight flight = flightsMap
						.get(dbScheduleFlight.getFlight().getId());
				return buildScheduleFLight(dbScheduleFlight, flight, airlinesMap);
			}).toList());
		}
		return travelSearchResult;
	}

	private ScheduleFlight buildScheduleFLight(FlightSchedule dbScheduleFlight,
			com.tkd.flight.mangement.app.entity.Flight flight, Map<UUID, Airline> airlinesMap) {
		ScheduleFlight scheduleFlight = new ScheduleFlight();
		scheduleFlight.setId(dbScheduleFlight.getFlight().getId());
		scheduleFlight.setAirlineName(airlinesMap.get(flight.getAirline().getId()).getName());
		scheduleFlight.setFlightNumber(flight.getFlightNumber());
		scheduleFlight.setGate(dbScheduleFlight.getGate());
		scheduleFlight.setDepartureTime(dbScheduleFlight.getDepartureTime());
		scheduleFlight.setDuration(dbScheduleFlight.getDuration());
		return scheduleFlight;
	}

	private Map<UUID, com.tkd.flight.mangement.app.entity.Flight> getFlightMapById(List<UUID> flightIds) {
		return flightRepository.findAllById(flightIds).stream()
				.collect(Collectors.toMap(com.tkd.flight.mangement.app.entity.Flight::getId, t -> t));
	}

	public List<Flight> searchFlightsByAirline(@NotBlank String airlineName) {
		Airline airline = airlineRepository.findByName(airlineName)
				.orElseThrow(() -> new InvalidAirlineException("No Such Airline Present:" + airlineName));

		return airline.getFlights().stream().map(dbFlight -> {
			Flight flight = new Flight();
			flight.setAirlineName(airline.getName());
			flight.setFlightNumber(dbFlight.getFlightNumber());
			flight.setId(dbFlight.getId());
			return flight;
		}).toList();
	}

	private void validateSourceAndDestination(String source, String destination, List<Airport> airports) {
		Set<String> airportNames = airports.stream().map(t -> t.getCode()).collect(Collectors.toSet());
		String errorMessage = "";
		if (!airportNames.contains(source)) {
			errorMessage += "Invalid Source Code:" + source + ";";
		}
		if (!airportNames.contains(destination)) {
			errorMessage += "Invalid Destination Code:" + destination + ";";
		}
		if (StringUtils.isNotBlank(errorMessage)) {
			throw new InvalidAirportException(errorMessage);
		}
	}
}
