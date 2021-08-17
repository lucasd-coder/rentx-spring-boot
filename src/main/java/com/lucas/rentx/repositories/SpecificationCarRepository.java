package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.SpecificationCar;

public interface SpecificationCarRepository extends JpaRepository<SpecificationCar, UUID> {

}
