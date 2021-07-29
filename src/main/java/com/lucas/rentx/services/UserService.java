package com.lucas.rentx.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.rentx.dto.UserDTO;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public User insert(User obj) {
		obj.setId(null);
		return userRepository.save(obj);
	}

	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getUsername(), pe.encode(objDto.getPassword()), objDto.getEmail(),
				objDto.getDriver_license(), null, new Date());
	}
}
