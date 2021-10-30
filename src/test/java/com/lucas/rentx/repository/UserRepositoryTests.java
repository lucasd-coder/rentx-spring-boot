package com.lucas.rentx.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	private UUID existingId;
	private UUID nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = UUID.fromString("420ee4ba-6eec-4f14-a237-14cc972d0494");
		nonExistingId = UUID.fromString("8458e545-03cb-4a68-a480-c0703745013a");
	}	
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalUserWhenIdExists() {
		Optional<User> result = userRepository.findById(existingId);
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalUserWhenIdDoesNotExists() {
		Optional<User> result = userRepository.findById(nonExistingId);
		Assertions.assertTrue(result.isEmpty());
	}	

}
