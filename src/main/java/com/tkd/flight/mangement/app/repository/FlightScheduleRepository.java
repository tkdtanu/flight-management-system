package com.tkd.flight.mangement.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.flight.mangement.app.entity.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, UUID> {

	List<FlightSchedule> findBySourceCodeAndDestinationCodeAndDepartureDate(String source, String destination,
			LocalDate departureDate);

	List<FlightSchedule> findBySourceCodeAndDestinationCode(String source, String destination);
}
