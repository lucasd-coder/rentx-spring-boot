package com.lucas.rentx.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.SpecificationDTO;
import com.lucas.rentx.services.SpecificationService;

@RestController
@RequestMapping(value = "/specifications")
public class SpecicationController {
	
	@Autowired
	private SpecificationService specificationService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<SpecificationDTO> insert(@Valid @RequestBody SpecificationDTO objDto) {
		objDto = specificationService.insert(objDto);		
		return ResponseEntity.status(HttpStatus.CREATED).body(objDto);
	}	
}
