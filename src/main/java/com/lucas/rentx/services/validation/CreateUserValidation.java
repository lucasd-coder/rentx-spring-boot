package com.lucas.rentx.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.rentx.controllers.exceptions.FieldMessage;
import com.lucas.rentx.dto.UserDTO;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.repositories.UserRepository;

public class CreateUserValidation implements ConstraintValidator<CreateUser, UserDTO> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		User checkEmailExist = userRepository.findByEmail(objDto.getEmail());

		if (checkEmailExist != null) {
			list.add(new FieldMessage("email", "campo Email já existente"));
		}

		User checkUsernameExist = userRepository.findByUsername(objDto.getUsername());

		if (checkUsernameExist != null) {
			list.add(new FieldMessage("username", "campo Username já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
