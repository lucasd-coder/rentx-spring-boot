package com.lucas.rentx.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.RentalCustomBodyDTO;
import com.lucas.rentx.dto.RentalDTO;
import com.lucas.rentx.services.DevolutionRentalService;
import com.lucas.rentx.services.RentalService;

@RestController
@RequestMapping(value = "/rentals")
public class RentalController {	
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private DevolutionRentalService devolutionRentalService;
	
	@PostMapping
	public ResponseEntity<RentalDTO> insert(@RequestBody RentalCustomBodyDTO objDto ) {
		RentalDTO obj = rentalService.insert(objDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	
	@PostMapping(value = "/devolution/{id}")
	public ResponseEntity<RentalDTO> devolution(@PathVariable("id") UUID id) {
		RentalDTO objDto = devolutionRentalService.devolutionRental(id);
		
		return ResponseEntity.ok(objDto);
		
	}

}
