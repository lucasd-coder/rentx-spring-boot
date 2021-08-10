package com.lucas.rentx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.rentx.dto.CategoryDTO;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}
	
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Category froDto(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName(), objDto.getDescription(), null);
	}
	
}
