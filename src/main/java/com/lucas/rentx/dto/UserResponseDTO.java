package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.lucas.rentx.entities.User;

public class UserResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private String name;

	private String username;

	private String email;

	private String avatar;

	private String driver_licence;

	private LocalDateTime created_at;

	public UserResponseDTO() {
	}
	
	public UserResponseDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		username = entity.getUsername();
		email = entity.getEmail();
		avatar = entity.getAvatar();
		driver_licence = entity.getDriverLicense();
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDriver_license() {
		return driver_licence;
	}

	public void setDriver_license(String driver_licence) {
		this.driver_licence = driver_licence;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

}
