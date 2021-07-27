package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_cars")
public class Car implements Serializable  {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	private String name;
	
	private String description;
	
	private Integer daily_rate;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean avaiable;
	
	private String license_plate;
	
	private Integer fine_amount;
	
	private String brand;
	
	private Date created_at;
	
	@OneToMany(mappedBy = "car")
	private List<Rental> rental = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories categories;
	
	@OneToMany(mappedBy = "cars_image")
	private List<CarImage> carImage = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.car")
	private Set<SpecificationCar> specifications = new HashSet<>();
	
	public Car() {		
	}

	public Car(UUID id, String name, String description, Integer daily_rate, Boolean avaiable, String license_plate,
			Integer fine_amount, String brand, Date created_at, Categories categories) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.daily_rate = daily_rate;
		this.avaiable = avaiable;
		this.license_plate = license_plate;
		this.fine_amount = fine_amount;
		this.brand = brand;
		this.created_at = created_at;
		this.categories = categories;
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

	public Integer getDaily_rate() {
		return daily_rate;
	}

	public void setDaily_rate(Integer daily_rate) {
		this.daily_rate = daily_rate;
	}

	public Boolean getAvaiable() {
		return avaiable;
	}

	public void setAvaiable(Boolean avaiable) {
		this.avaiable = avaiable;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public Integer getFine_amount() {
		return fine_amount;
	}

	public void setFine_amount(Integer fine_amount) {
		this.fine_amount = fine_amount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}	

	public List<Rental> getRentals() {
		return rental;
	}		

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}		

	public List<CarImage> getCarsImage() {
		return carImage;
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
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}	
}
