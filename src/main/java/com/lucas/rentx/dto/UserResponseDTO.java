package com.lucas.rentx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.lucas.rentx.entities.User;

public class UserResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;	
	
	private String name;
		
	private String username;
		
	private String email;
	
	private String driver_license;

	private Date created_at;

	public UserResponseDTO() {
	}

	public UserResponseDTO(UUID id, String name, String username, String email, String driver_license,
			Date created_at) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;	
		this.email = email;
		this.driver_license = driver_license;
		this.created_at = created_at;
	}

	public UserResponseDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		username = entity.getUsername();
		email = entity.getEmail();
		driver_license = entity.getDriver_license();
		created_at = entity.getCreated_at();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDriver_license() {
		return driver_license;
	}

	public void setDriver_license(String driver_license) {
		this.driver_license = driver_license;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
