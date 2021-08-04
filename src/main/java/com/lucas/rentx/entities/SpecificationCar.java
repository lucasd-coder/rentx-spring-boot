package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_specifications_cars")
public class SpecificationCar implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private SpecificationCarPk id = new SpecificationCarPk();

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public SpecificationCar() {
	}

	public SpecificationCar(Car car, Specification specification, LocalDateTime createAt) {
		super();
		id.setCars(car);
		id.setSpecifications(specification);
		this.createdAt = createAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

	public SpecificationCarPk getId() {
		return id;
	}

	public void setId(SpecificationCarPk id) {
		this.id = id;
	}

	@JsonIgnore
	public Specification getSpecification() {
		return id.getSpecifications();
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
