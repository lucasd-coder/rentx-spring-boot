package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, UUID>{

	Rental findByCarIdAndEndDateIsNull(UUID carId);

	Rental findByUserIdAndEndDateIsNull(UUID userId);
}
	