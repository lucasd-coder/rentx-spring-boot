package com.lucas.rentx.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.lucas.rentx.entities.Specification;
import com.lucas.rentx.services.validation.CreateSpecification;

@CreateSpecification
public class SpecificationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	@NotEmpty(message = "name não dever nulo")
	private String name;
	
	@NotEmpty(message= "description não dever nulo")
	private String description;

	public SpecificationDTO() {
	}

	public SpecificationDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public SpecificationDTO(Specification entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
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
}
