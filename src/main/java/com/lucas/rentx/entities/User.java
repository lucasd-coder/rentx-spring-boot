package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	private String name;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String driver_licence;
	
	private Date created_at;
	
	@OneToMany(mappedBy = "user")
	private List<Rentals> rentals = new ArrayList<>();
	
	public User() {}

	public User(UUID id, String name, String password, String email, String driver_licence, Date create_at) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.driver_licence = driver_licence;
		this.created_at = create_at;
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date create_at) {
		this.created_at = create_at;
	}	

	public List<Rentals> getRentals() {
		return rentals;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
