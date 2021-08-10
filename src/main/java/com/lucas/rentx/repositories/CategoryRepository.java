package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
	
	Category findByName(String name);

}
