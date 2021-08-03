package com.lucas.rentx.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucas.rentx.services.SeedService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private SeedService seedService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		seedService.instantiateTestDatabase();
		return true;
	}
		
}
