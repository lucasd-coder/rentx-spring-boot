package com.lucas.rentx.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class SeedService {

	@Autowired
	private UserRepository userRepository;
	
	
	public void instantiateTestDatabase() throws ParseException {
			
		User user1 = new User(null, "Maria", "123456", "maria@gmail.com", "123456789", null, new Date());
		User admin = new User(null, "João", "123456", "joao@gmail.com", "123456789", null, new Date());
		
		userRepository.saveAll(Arrays.asList(user1, admin));
		
	}
	
	
}
			
	