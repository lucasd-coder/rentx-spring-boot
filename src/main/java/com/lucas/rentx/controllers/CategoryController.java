package com.lucas.rentx.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lucas.rentx.dto.CategoryDTO;
import com.lucas.rentx.services.CategoryService;
import com.lucas.rentx.services.ImportCategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ImportCategoryService importCategoryService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto) {
		objDto = categoryService.insert(objDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<Page<CategoryDTO>> findAll(
			@PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<CategoryDTO> list = categoryService.findAll(pageable);

		return ResponseEntity.ok(list);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/import")
	public ResponseEntity<Void> importFile(@RequestParam("file") MultipartFile file) {
		importCategoryService.loadCategory(file);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
