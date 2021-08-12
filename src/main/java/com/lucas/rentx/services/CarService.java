package com.lucas.rentx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public Car insert(Car obj) {
		obj.setId(null);
		return carRepository.save(obj);
	}

	public Car fromDto(CarDTO objDto) {
		return new Car(objDto.getId(), objDto.getName(), objDto.getDescription(), objDto.getDaily_rate(), true,
				objDto.getLicense_plate(), objDto.getFine_amount(), objDto.getBrand(), null, null);
	}
}