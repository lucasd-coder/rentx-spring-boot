package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.lucas.rentx.entities.Car;

public class CarResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private Boolean avaiable;

	private String name;

	private String description;

	private Integer daily_rate;

	private Integer fine_amount;

	private String license_plate;

	private String brand;

	private UUID category_id;

	private LocalDateTime created_at;

	public CarResponseDTO() {
	}

	public CarResponseDTO(Car entity) {
		id = entity.getId();
		avaiable = entity.getAvaiable();
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

	public Boolean getAvaiable() {
		return avaiable;
	}

	public void setAvaiable(Boolean avaiable) {
		this.avaiable = avaiable;
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
