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

public class CreateUserValidation implements ConstraintValidator<CreateUser, UserDTO>{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		User checkEmailExist = userRepository.findByEmail(objDto.getEmail());
		
		if(checkEmailExist != null) {
			list.add(new FieldMessage("email", "campo Email já existente"));
		}
		
		User checkUsernameExist = userRepository.findByUsername(objDto.getUsername());
		
		if(checkUsernameExist != null) {
			list.add(new FieldMessage("username", "campo Username já existente"));
		}
		
		if(objDto.getName().length() < 4 && objDto.getName().length() > 255) {
			list.add(new FieldMessage("name", "campo name deve ser entre 4 e 255 caracteres"));
		}
		
		if(objDto.getUsername().length() < 4 && objDto.getUsername().length() > 255) {
			list.add(new FieldMessage("username", "campo name deve ser entre 4 e 255 caracteres"));
		}
		
		if(objDto.getPassword().length() < 6) {
			list.add(new FieldMessage("password", "campo password deve ter no mínimo 4 caracteres"));
		}						
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
