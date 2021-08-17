package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, UUID>{
	
	Specification findByName(String name);

}
