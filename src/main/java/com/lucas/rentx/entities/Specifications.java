package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Specifications implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	private String name;
	
	private String description;
	
	private Date created_at;
	
	public Specifications() {		
	}

	public Specifications(UUID id, String name, String description, Date created_at) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
		Specifications other = (Specifications) obj;
		return Objects.equals(id, other.id);
	}		

}
