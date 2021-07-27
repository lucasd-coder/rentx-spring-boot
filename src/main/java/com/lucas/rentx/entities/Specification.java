package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_specifications")
public class Specification implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	private String name;
	
	private String description;
	
	private Date created_at;
	
	@OneToMany(mappedBy = "id.specification")
	private Set<SpecificationCar> specifications = new HashSet<>();
	
	public Specification() {		
	}

	public Specification(UUID id, String name, String description, Date created_at) {
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

	public Set<SpecificationCar> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(Set<SpecificationCar> specifications) {
		this.specifications = specifications;
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
		Specification other = (Specification) obj;
		return Objects.equals(id, other.id);
	}		

}