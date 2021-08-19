package com.lucas.rentx.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.repositories.CarRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CategoryService categoryService;

	public Car find(UUID id) {
		Optional<Car> obj = carRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	@Transactional()
	public CarDTO insert(CarDTO obj, UUID id) {
		Car car = fromDto(obj);
		car.setCategory(categoryService.find(id));
		carRepository.save(car);
		return new CarDTO(car);
	}

	public Car fromDto(CarDTO objDto) {
		return new Car(null, objDto.getName(), objDto.getDescription(), objDto.getDaily_rate(), true,
				objDto.getLicense_plate(), objDto.getFine_amount(), objDto.getBrand(), null, null);
	}
}
