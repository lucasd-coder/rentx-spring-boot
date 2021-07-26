package com.lucas.rentx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Rentals implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	private Date start_date;
	
	private Date end_date;
	
	private Date expected_return_date;
	
	private Date created_at;
	
	private Date updated_at;
	
	private Users user;
	
	public Rentals() {		
	}

	public Rentals(UUID id, Date start_date, Date end_date, Date expected_return_date, Date created_at,
			Date updated_at) {
		super();
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.expected_return_date = expected_return_date;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getExpected_return_date() {
		return expected_return_date;
	}

	public void setExpected_return_date(Date expected_return_date) {
		this.expected_return_date = expected_return_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
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
		Rentals other = (Rentals) obj;
		return Objects.equals(id, other.id);
	}

}
