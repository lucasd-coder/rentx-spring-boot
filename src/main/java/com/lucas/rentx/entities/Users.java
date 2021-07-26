package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String driver_licence;
	
	private Date create_at;
		
	private Rentals rentals;
	
	public Users() {}

	public Users(UUID id, String name, String password, String email, String driver_licence, Date create_at) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.driver_licence = driver_licence;
		this.create_at = create_at;
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

	public String getDriver_licence() {
		return driver_licence;
	}

	public void setDriver_licence(String driver_licence) {
		this.driver_licence = driver_licence;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
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
		Users other = (Users) obj;
		return Objects.equals(id, other.id);
	}

}
