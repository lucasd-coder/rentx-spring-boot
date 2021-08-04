package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.lucas.rentx.entities.User;
import com.lucas.rentx.services.validation.CreateUser;

@CreateUser
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;	
	
	@NotBlank(message= "name não dever nulo")
	private String name;
	
	@NotBlank(message= "username não dever nulo")
	private String username;
	
	@NotBlank(message= "password não dever nulo")
	private String password;
	
	@Email(message="Email inválido")
	private String email;
	
	@NotBlank(message = "não pode ser nulo")
	private String driver_license;

	private LocalDateTime created_at;

	public UserDTO() {
	}

	public UserDTO(UUID id, String name, String username, String password, String email, String driver_license,
			LocalDateTime created_at) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.driver_license = driver_license;
		this.created_at = created_at;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		username = entity.getUsername();
		password = entity.getPassword();
		email = entity.getEmail();
		driver_license = entity.getDriverLicense();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}
