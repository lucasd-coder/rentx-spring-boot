package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.Car;

public interface CarRepository extends JpaRepository<Car, UUID> {
	
	Car findByLicensePlate(String licensePlate);
}
