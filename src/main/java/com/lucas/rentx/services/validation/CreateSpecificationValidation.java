package com.lucas.rentx.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.rentx.controllers.exceptions.FieldMessage;
import com.lucas.rentx.dto.SpecificationDTO;
import com.lucas.rentx.entities.Specification;
import com.lucas.rentx.repositories.SpecificationRepository;

public class CreateSpecificationValidation implements ConstraintValidator<CreateSpecification, SpecificationDTO> {
	
	@Autowired
	private SpecificationRepository specificationRepository;

	@Override
	public boolean isValid(SpecificationDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Specification checNamekExist = specificationRepository.findByName(objDto.getName());

		if (checNamekExist != null) {
			list.add(new FieldMessage("name", "Specification Already exists!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

}
