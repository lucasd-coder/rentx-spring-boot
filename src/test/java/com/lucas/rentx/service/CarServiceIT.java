package com.lucas.rentx.service;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.services.CarService;
import com.lucas.rentx.tests.Factory;

@SpringBootTest
@Transactional
public class CarServiceIT {

	@Autowired
	private CarService carService;		

	private UUID existingId;	
	private Car car;
	private UUID categoryId;
	private CarDTO carDTO;	

	@BeforeEach
	void setUp() throws Exception {

		existingId = UUID.fromString("db155f29-1972-4e8b-adec-c8c1bc089e28");		
		categoryId = UUID.fromString("cf4957be-8b59-40bd-8681-7dc2a50ee570");
		car = Factory.createCar();			
		carDTO = Factory.createCarDTO();
	}
	
	@Test
	public void insertShouldPersistNewCarDTO() {
		carDTO = carService.insert(carDTO, categoryId);
		Assertions.assertEquals(carDTO.getId(), existingId);
	}

	@Test
	public void listAvailableShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));

		Page<CarResponseDTO> result = carService.listAvailable(
				car.getName(), 
				car.getBrand(), 
				car.getCategory().getId(),
				pageable);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(5, result.getSize());
	}

	@Test
	public void listAvailableShouldReturnSortedPageWhenSortByName() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));

		Page<CarResponseDTO> result = carService.listAvailable(
				car.getName(), 
				car.getBrand(), 
				car.getCategory().getId(),
				pageable);

		Assertions.assertFalse(result.isEmpty());		
	}

}
