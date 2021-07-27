package com.lucas.rentx.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;	
	
	public User insert(User obj) {
		obj.setId(null);
		obj.setCreated_at(new Date());
		return userRepository.save(obj);		
	}
	

}
