package com.lucas.rentx.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class SeedService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		User user1 = new User(null, "Maria", "maria123", pe.encode("123456"), "maria@gmail.com", "123456789", null, null);
		User admin = new User(null, "João", "joao123", pe.encode("123456"), "joao@gmail.com", "123456789", null, null);

		userRepository.saveAll(Arrays.asList(user1, admin));

	}

}
