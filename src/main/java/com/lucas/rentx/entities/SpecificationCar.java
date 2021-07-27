package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_specifications_cars")
public class SpecificationCar implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private SpecificationCarPk id = new SpecificationCarPk();
		
	private Date created_at;
	
	public SpecificationCar() {		
	}

	public SpecificationCar(Car car, Specification specification ,Date created_at) {
		super();
		id.setCars(car);
		id.setSpecifications(specification);
		this.created_at = created_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public SpecificationCarPk getId() {
		return id;
	}	

	public void setId(SpecificationCarPk id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Specification getSpecification() {		
		 return	id.getSpecifications();
	}
	
	public void setSpecifications(Specification specification) {
		id.setSpecifications(specification);
	}
	
	public Car getCars() {
		return id.getCars();
	}
	
	public void setCars(Car car) {
		id.setCars(car);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecificationCar other = (SpecificationCar) obj;
		return Objects.equals(id, other.id);
	}	
		
}
