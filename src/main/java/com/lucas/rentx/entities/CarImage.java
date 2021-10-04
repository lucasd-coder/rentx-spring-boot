package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cars_image")
public class CarImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
	private UUID id;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public CarImage() {
	}

	public CarImage(UUID id, String imageName, LocalDateTime createAt, Car car) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.createdAt = createAt;
		this.car = car;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
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
		CarImage other = (CarImage) obj;
		return Objects.equals(id, other.id);
	}

}
