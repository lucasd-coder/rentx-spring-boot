package com.lucas.rentx.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cars")
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
	private UUID id;

	private String name;

	private String description;

	@Column(name = "daily_rate")
	private Integer dailyRate;

	@Column(columnDefinition = "boolean default true")
	private Boolean avaiable;

	@Column(name = "license_plate", unique = true)
	private String licensePlate;

	@Column(name = "fine_amount")
	private Integer fineAmount;

	private String brand;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "car")
	private List<Rental> rental = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "car")
	private List<CarImage> images = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.car")
	private Set<SpecificationCar> specifications = new HashSet<>();

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

	public Car() {
	}

	public Car(UUID id, String name, String description, Integer dailyRate, Boolean avaiable, String licensePlate, Integer fineAmount,
			String brand, LocalDateTime createAt, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dailyRate = dailyRate;
		this.avaiable = avaiable;
		this.licensePlate = licensePlate;
		this.fineAmount = fineAmount;
		this.brand = brand;
		this.createdAt = createAt;
		this.category = category;
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

	public Integer getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(Integer daily_rate) {
		this.dailyRate = daily_rate;
	}

	public Boolean getAvaiable() {
		return avaiable;
	}

	public void setAvaiable(Boolean avaiable) {
		this.avaiable = avaiable;
	}
		
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Integer fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

	public List<Rental> getRentals() {
		return rental;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CarImage> getImages() {
		return images;
	}		
		
	public Set<SpecificationCar> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<SpecificationCar> specifications) {
		this.specifications = specifications;
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
