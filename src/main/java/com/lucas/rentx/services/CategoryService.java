package com.lucas.rentx.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.CategoryDTO;
import com.lucas.rentx.entities.Category;
import com.lucas.rentx.repositories.CategoryRepository;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category find(UUID id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ""));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(Pageable pageable) {
		Page<Category> result = categoryRepository.findAll(pageable);
		return result.map(obj -> new CategoryDTO(obj));
	}

	public Category froDto(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName(), objDto.getDescription(), null);
	}
	
}
