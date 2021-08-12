package com.lucas.rentx.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.services.CarService;
import com.lucas.rentx.services.CategoryService;

@RestController
@RequestMapping(value= "/cars")
public class CarController {
		
	@Autowired
	private CarService carService;
	
	@Autowired
	private CategoryService categoryService;	
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Void> insert(@Valid @RequestBody CarDTO objDto, @PathVariable UUID id) {
		Car obj = carService.fromDto(objDto);
		obj.setCategories(categoryService.find(id));
		obj = carService.insert(obj);		
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	

}
