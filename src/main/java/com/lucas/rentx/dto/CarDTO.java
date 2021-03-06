package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.lucas.rentx.entities.Car;
import com.lucas.rentx.services.validation.CreateCar;

@CreateCar
public class CarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	@NotEmpty(message = "name não dever nulo")
	private String name;

	@NotEmpty(message = "description não dever nulo")
	private String description;

	@NotNull(message = "daily_rate não dever nulo")
	private Integer daily_rate;

	@NotNull(message = "fine_amount não dever nulo")
	private Integer fine_amount;

	@NotEmpty(message = "license_plate não dever nulo")
	private String license_plate;

	@NotEmpty(message = "brand não dever nulo")
	private String brand;

	private UUID category_id;

	private LocalDateTime created_at;

	public CarDTO() {
	}

	public CarDTO(UUID id, String name, String description, Integer daily_rate, Integer fine_amount,
			String license_plate, String brand, UUID category_id, LocalDateTime created_at) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.daily_rate = daily_rate;
		this.fine_amount = fine_amount;
		this.license_plate = license_plate;
		this.brand = brand;
		this.category_id = category_id;
		this.created_at = created_at;
	}

	public CarDTO(Car entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		daily_rate = entity.getDailyRate();
		fine_amount = entity.getFineAmount();
		license_plate = entity.getLicensePlate();
		brand = entity.getBrand();
		category_id = entity.getCategory().getId();
		created_at = entity.getCreatedAt();
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

	public Integer getFine_amount() {
		return fine_amount;
	}

	public void setFine_amount(Integer fine_amount) {
		this.fine_amount = fine_amount;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public UUID getCategory_id() {
		return category_id;
	}

	public void setCategory_id(UUID category_id) {
		this.category_id = category_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

}
