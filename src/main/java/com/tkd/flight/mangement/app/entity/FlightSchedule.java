package com.tkd.flight.mangement.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.FutureOrPresent;

@Entity(name = "FlightSchedule")
@Table(name = "FlightSchedule", uniqueConstraints = {
		@UniqueConstraint(name = "UK_SourceAirportId_AND_DestinationAirportId_AND_FlightId_AND_DepartureDate", columnNames = {
				"source_airport_id", "destination_airport_id", "flight_id", "departure_date" }) })
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid default random_uuid()")
	private UUID id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "FK_FlightSchedule_Flight_FlightId"))
	private Flight flight;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "source_airport_id", foreignKey = @ForeignKey(name = "FK_FlightSchedule_SourceAirport_AirportId"))
	private Airport source;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_airport_id", foreignKey = @ForeignKey(name = "FK_FlightSchedule_DestinationAirport_AirportId"))
	private Airport destination;

	private int duration;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "departure_time")
	@FutureOrPresent
	private LocalDateTime departureTime;

	@Temporal(TemporalType.DATE)
	@FutureOrPresent
	@Column(name = "departure_date")
	private LocalDate departureDate;

	private String gate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = FlightScheduleSeat.class, mappedBy = "flightSchedule")
	private List<FlightScheduleSeat> flightScheduleSeats;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public List<FlightScheduleSeat> getFlightScheduleSeats() {
		return flightScheduleSeats;
	}

	public void setFlightScheduleSeats(List<FlightScheduleSeat> flightScheduleSeats) {
		this.flightScheduleSeats = flightScheduleSeats;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

}
