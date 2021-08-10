package com.lucas.rentx.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.rentx.controllers.exceptions.FieldMessage;
import com.lucas.rentx.dto.CategoryDTO;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.repositories.CategoryRepository;

public class CreateCategoryValidation implements ConstraintValidator<CreateCategory, CategoryDTO> {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean isValid(CategoryDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Category aux = categoryRepository.findByName(objDto.getName());
		
		if (aux != null) {
			list.add(new FieldMessage("name", "category já existe"));
		}
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
