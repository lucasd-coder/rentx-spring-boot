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
	
	@Transactional()
	public CategoryDTO insert(CategoryDTO obj) {		
		Category category = fromDto(obj);
		categoryRepository.save(category);
		return new CategoryDTO(category);
	}
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(Pageable pageable) {
		Page<Category> result = categoryRepository.findAll(pageable);
		return result.map(obj -> new CategoryDTO(obj));
	}

	public Category fromDto(CategoryDTO objDto) {
		return new Category(null, objDto.getName(), objDto.getDescription(), null);
	}
	
}
