package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Cars_image implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	private String image_name;
	
	private Date created_at;
	
	public Cars_image() {		
	}

	public Cars_image(UUID id, String image_name, Date created_at) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.created_at = created_at;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
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
		Cars_image other = (Cars_image) obj;
		return Objects.equals(id, other.id);
	}	

}
