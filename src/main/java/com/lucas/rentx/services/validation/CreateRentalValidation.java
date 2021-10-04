package com.lucas.rentx.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.rentx.controllers.exceptions.FieldMessage;
import com.lucas.rentx.dto.RentalDTO;
import com.lucas.rentx.entities.Rental;
import com.lucas.rentx.repositories.RentalRepository;

public class CreateRentalValidation implements ConstraintValidator<CreateRental, RentalDTO> {

	@Autowired
	private RentalRepository rentalRepository;

	@Override
	public boolean isValid(RentalDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();		

		Rental checkByCarId = rentalRepository.findByCarIdAndEndDateIsNull(objDto.getCar().getId());

		if (checkByCarId != null) {
			list.add(new FieldMessage("car", "Car is unavailable"));
		}

		Rental checkByUserId = rentalRepository.findByUserIdAndEndDateIsNull(objDto.getUser().getId());

		if (checkByUserId != null) {
			list.add(new FieldMessage("user", "There's a rental in progress Car user!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
