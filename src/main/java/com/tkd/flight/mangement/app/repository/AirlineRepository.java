package com.tkd.flight.mangement.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkd.flight.mangement.app.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, UUID> {
	@Cacheable(cacheNames = "airline", key = "name")
	Optional<Airline> findByName(String name);

	@Cacheable(cacheNames = "airlines")
	List<Airline> findAll();
}
