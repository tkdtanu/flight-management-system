package com.tkd.flight.mangement.app.entity;

import java.util.UUID;

import com.tkd.flight.mangement.app.dto.enums.BookingStatus;

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

@Entity(name = "FlightScheduleSeat")
@Table(name = "FlightScheduleSeat")
public class FlightScheduleSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "uuid default random_uuid()")
	private UUID id;
	
	private int fare;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "flight_schedule_id")
	private FlightSchedule flightSchedule;

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
}
