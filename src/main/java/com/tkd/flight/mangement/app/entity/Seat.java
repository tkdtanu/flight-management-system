package com.tkd.flight.mangement.app.entity;

import java.util.UUID;

import com.tkd.flight.mangement.app.dto.enums.SeatClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Seat")
@Table(name = "Seat", uniqueConstraints = { @UniqueConstraint(name = "UK_FlightId_AND_SeatNumber", columnNames = { "flight_id", "seat_number" }) })
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid default random_uuid()")
	private UUID id;

	@Column(name = "seat_number")
	private String seatNumber;

	@Enumerated(EnumType.STRING)
	private SeatClass seatClass;

	@ManyToOne(optional = false, targetEntity = Flight.class)
	@JoinColumn(name = "flight_id")
	private Flight flight;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public SeatClass getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(SeatClass seatClass) {
		this.seatClass = seatClass;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
