package com.lucas.rentx.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.rentx.controllers.exceptions.FieldMessage;
import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.repositories.CarRepository;

public class CreateCarValidation implements ConstraintValidator<CreateCar, CarDTO>{
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public boolean isValid(CarDTO objDto, ConstraintValidatorContext context) {
		 
		List<FieldMessage> list = new ArrayList<>();
		
		Car carCheckExist = carRepository.findByLicensePlate(objDto.getLicense_plate());
		if(carCheckExist != null) {
			list.add(new FieldMessage("license_plate", "License plate já existente"));
		}		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
