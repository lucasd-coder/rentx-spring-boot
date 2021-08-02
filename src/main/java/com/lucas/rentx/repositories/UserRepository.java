package com.lucas.rentx.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.rentx.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByEmail(String email);
	
	User findByUsername(String username);
		
}
