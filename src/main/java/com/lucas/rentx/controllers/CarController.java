package com.lucas.rentx.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.services.CarService;

@RestController
@RequestMapping(value= "/cars")
public class CarController {
		
	@Autowired
	private CarService carService;		
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/{id}")
	public ResponseEntity<CarDTO> insert(@Valid @RequestBody CarDTO objDto, @PathVariable UUID id) {
		objDto = carService.insert(objDto, id);		
		return ResponseEntity.status(HttpStatus.CREATED).body(objDto);
	}		
}
