package com.lucas.rentx.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
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

	@GetMapping(value = "/available")
	public ResponseEntity<Page<CarResponseDTO>> listAvailable(
			@PageableDefault(page = 0, size = 5, sort = "name", direction = Direction.ASC ) Pageable pageable,
			@RequestParam(value = "brand", required = false) String brand, 
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category_id", required = false) UUID categoryId) {

		Page<CarResponseDTO> objDto = carService.listAvailable(name, brand, categoryId, pageable);

		return ResponseEntity.ok(objDto);
	}


}
