package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.lucas.rentx.entities.Car;

public interface CarRepository extends JpaRepository<Car, UUID>, QuerydslPredicateExecutor<Car> {

	Car findByLicensePlate(String licensePlate);

	Page<Car> findByAvaiableTrueAndNameAndBrandAndCategoryId(String name, String brand, UUID categoryId,
			Pageable pageable);
}
