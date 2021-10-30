package com.lucas.rentx.repositories.querys;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lucas.rentx.entities.Car;

public interface CarRepositoriyQuery {
	
	void updateAvailable(UUID id, boolean available);
	
	Page<Car> findAvailable(String name, String brand, UUID categoryId, Pageable pageablef);
}
