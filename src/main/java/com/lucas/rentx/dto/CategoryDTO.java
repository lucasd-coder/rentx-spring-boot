package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.lucas.rentx.entities.Category;
import com.lucas.rentx.services.validation.CreateCategory;

@CreateCategory
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	
	@NotBlank(message= "name não dever nulo")
	private String name;
	
	@NotBlank(message= "description não dever nulo")
	private String description;

	private LocalDateTime created_at;

	public CategoryDTO() {
	}

	public CategoryDTO(UUID id, String name, String description, LocalDateTime created_at) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created_at = created_at;
	}

	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		created_at = entity.getCreatedAt();
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

}
