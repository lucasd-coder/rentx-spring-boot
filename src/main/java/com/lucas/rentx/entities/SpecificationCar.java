package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_specifications_cars")
public class SpecificationCar implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	SpecificationCarPk id = new SpecificationCarPk();

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public SpecificationCar() {
	}

	public SpecificationCar(Car car, Specification specification, LocalDateTime createAt) {
		super();
		id.setCar(car);
		id.setSpecification(specification);
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
		return id.getSpecification();
	}

	public void setSpecification(Specification specification) {
		id.setSpecification(specification);
	}

	public Car getCar() {
		return id.getCar();
	}

	public void setCar(Car car) {
		id.setCar(car);
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
