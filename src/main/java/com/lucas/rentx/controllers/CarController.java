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
import com.lucas.rentx.dto.SpecificationCarDTO;
import com.lucas.rentx.dto.SpecificationCarIdDTO;
import com.lucas.rentx.services.CarService;
import com.lucas.rentx.services.SpecificationCarService;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private SpecificationCarService specificationCarService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/{id}")
	public ResponseEntity<CarDTO> insert(@Valid @RequestBody CarDTO objDto, @PathVariable UUID id) {
		objDto = carService.insert(objDto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(objDto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/specifications/{car_id}")
	public ResponseEntity<SpecificationCarDTO> insertCarSpecification(
			@RequestBody SpecificationCarIdDTO specificationId, @PathVariable("car_id") UUID carId) {

		SpecificationCarDTO objDto = specificationCarService.insert(carId, specificationId);

		return ResponseEntity.status(HttpStatus.CREATED).body(objDto);
	}

}
