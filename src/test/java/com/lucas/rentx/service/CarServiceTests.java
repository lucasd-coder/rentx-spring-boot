package com.lucas.rentx.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.repositories.CarRepository;
import com.lucas.rentx.repositories.querys.impl.CarRepositoriyQueryImpl;
import com.lucas.rentx.services.CarService;
import com.lucas.rentx.services.CategoryService;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;
import com.lucas.rentx.tests.Factory;

@ExtendWith(SpringExtension.class)
public class CarServiceTests {

	@InjectMocks
	private CarService carService;

	@Mock
	private CarRepository carRepository;
	
	@Mock
	private CarRepositoriyQueryImpl carRepositoriyQueryImpl;

	@Mock
	private CategoryService categoryService;

	private UUID existingId;
	private UUID nonExistingId;
	private Car car;
	private UUID categoryId;
	private CarDTO carDTO;
	private PageImpl<Car> page;
	private Category category;		
	private Pageable pageable;

	@BeforeEach
	void setUp() throws Exception {

		existingId = UUID.fromString("55ae7f39-0ac9-4386-a315-c07ae02ec567");
		nonExistingId = UUID.fromString("8458e545-03cb-4a68-a480-c0703745013a");
		categoryId = UUID.fromString("cf4957be-8b59-40bd-8681-7dc2a50ee570");
		car = Factory.createCar();
		page = new PageImpl<>(List.of(car));
		category = Factory.createCategory();
		carDTO = Factory.createCarDTO();
		pageable = PageRequest.of(0, 5, Sort.by("name"));
		
		Mockito.when(carRepositoriyQueryImpl.findAvailable(car.getName(), car.getBrand(), car.getCategory().getId(), pageable)).thenReturn(page);
		Mockito.when(carRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		Mockito.when(carRepository.findById(existingId)).thenReturn(Optional.of(car));
		Mockito.when(carRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		Mockito.when(carRepository.save(car)).thenReturn(car);
		Mockito.when(categoryService.find(categoryId)).thenReturn(category);
	}

	@Test
	public void findShouldReturnCarWhenIdExists() {
		Car obj = carService.find(existingId);

		Assertions.assertNotNull(obj);

		Mockito.verify(carRepository, Mockito.times(1)).findById(existingId);
	}

	@Test
	public void findShouldReturnCarWhenIdDoesNotExists() {

		Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			carService.find(nonExistingId);
		});

		Mockito.verify(carRepository, Mockito.times(1)).findById(nonExistingId);
	}

	@Test
	public void insertShouldPersistNewCarDTO() {
		carDTO = carService.insert(carDTO, categoryId);
		Assertions.assertNotNull(carDTO.getId());
	}

	@Test
	public void listAvailableShouldReturnPage() {
						
		Page<CarResponseDTO> result = carService.listAvailable(
				car.getName(), 
				car.getBrand(), 
				car.getCategory().getId(),
				pageable);
		
		Assertions.assertNotNull(result.getContent());		
	}

}
