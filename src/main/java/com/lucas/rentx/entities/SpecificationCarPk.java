package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SpecificationCarPk implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "specification_id")
	private Specification specification;

	public Car getCars() {
		return car;
	}

	public void setCars(Car car) {
		this.car = car;
	}

	public Specification getSpecifications() {
		return specification;
	}

	public void setSpecifications(Specification specification) {
		this.specification = specification;
	}

	@Override
	public int hashCode() {
		return Objects.hash(car, specification);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecificationCarPk other = (SpecificationCarPk) obj;
		return Objects.equals(car, other.car) && Objects.equals(specification, other.specification);
	}

}
