package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.lucas.rentx.entities.SpecificationCar;

public class SpecificationCarDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CarDTO car;
	
	private SpecificationDTO specification;
	
	private LocalDateTime created_at;
	
	public SpecificationCarDTO() {		
	}

	public SpecificationCarDTO(CarDTO car, SpecificationDTO specification, LocalDateTime created_at) {
		super();
		this.car = car;
		this.specification = specification;
		this.created_at = created_at;
	}
	
	public SpecificationCarDTO(SpecificationCar entity) {
		car = new CarDTO(entity.getCar());
		specification = new SpecificationDTO(entity.getSpecification());
		created_at = entity.getCreatedAt();
	}

	public CarDTO getCar() {
		return car;
	}

	public void setCar(CarDTO car) {
		this.car = car;
	}

	public SpecificationDTO getSpecification() {
		return specification;
	}

	public void setSpecification(SpecificationDTO specification) {
		this.specification = specification;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}	

}
