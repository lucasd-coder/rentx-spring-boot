package com.lucas.rentx.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.rentx.entities.UserToken;
import com.lucas.rentx.repositories.UserRepository;
import com.lucas.rentx.repositories.UserTokenRepository;
import com.lucas.rentx.services.exceptions.DataIntegrityException;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class ResetPasswordService {
	
	
	@Autowired
	private UserTokenRepository userTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void execute(UUID token, String password) {
		UserToken checkExistToken = userTokenRepository.findByRefreshToken(token);		
		
		if (checkExistToken == null) {
			throw new ObjectNotFoundException("não encontrado");
		}
				
		if(comapreIfBefore(checkExistToken.getExpiresDate(), new Date())) {
			throw  new DataIntegrityException("Token expired!");
		}
		
		checkExistToken.getTokenUser().setPassword(pe.encode(password));
		
		userRepository.save(checkExistToken.getTokenUser());
		
		userTokenRepository.deleteById(checkExistToken.getId());
				
	}
	
	private Boolean comapreIfBefore(Date start, Date expire) {		
		if(start.before(expire)) {
			return true;
		}
		return false;
	}

}
