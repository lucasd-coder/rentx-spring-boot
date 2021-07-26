package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Cars implements Serializable  {
		
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	private String name;
	
	private String description;
	
	private Integer daily_rate;
	
	private Boolean avaiable;
	
	private String license_plate;
	
	private Integer fine_amount;
	
	private String brand;
	
	private Date created_at;
	
	public Cars() {		
	}

	public Cars(UUID id, String name, String description, Integer daily_rate, Boolean avaiable, String license_plate,
			Integer fine_amount, String brand, Date created_at) {
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
		Cars other = (Cars) obj;
		return Objects.equals(id, other.id);
	}	
}
