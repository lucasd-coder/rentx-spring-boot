package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_cars_image")
public class CarImage implements Serializable {
		
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	private String image_name;
	
	private Date created_at;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car cars_image;
	
	public CarImage() {		
	}

	public CarImage(UUID id, String image_name, Date created_at, Car cars_image) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.created_at = created_at;
		this.cars_image = cars_image;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}	

	public Car getCarsImage() {
		return cars_image;
	}

	public void setCars(Car car) {
		this.cars_image = car;
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
