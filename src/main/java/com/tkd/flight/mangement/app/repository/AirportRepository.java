package com.tkd.flight.mangement.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.flight.mangement.app.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {

	@Cacheable(value = "airports")
	List<Airport> findAll();
}
