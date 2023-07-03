package com.tkd.flight.mangement.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.flight.mangement.app.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
	List<Flight> findByAirlineName(String airlineName);
}
