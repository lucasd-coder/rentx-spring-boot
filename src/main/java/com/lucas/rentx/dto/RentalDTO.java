package com.lucas.rentx.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.lucas.rentx.entities.Rental;
import com.lucas.rentx.services.validation.CreateRental;

@CreateRental
public class RentalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private Date start_date;

	private Date end_date;

	private Date expected_return_date;

	private Long total;

	private UserDTO user;

	private CarDTO car;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	public RentalDTO() {
	}

	public RentalDTO(UUID id, Date start_date, Date end_date, Long total, Date expected_return_date, UserDTO user,
			CarDTO car, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.expected_return_date = expected_return_date;
		this.total = total;
		this.user = user;
		this.car = car;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public RentalDTO(Rental entity) {
		id = entity.getId();
		start_date = entity.getStartDate();
		end_date = entity.getEndDate();
		expected_return_date = entity.getExpectedReturnDate();
		total = entity.getTotal();
		user = new UserDTO(entity.getUser());
		car = new CarDTO(entity.getCar());
		created_at = entity.getCreatedAt();
		updated_at = entity.getUpdatedAt();
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CarDTO getCar() {
		return car;
	}

	public void setCar(CarDTO car) {
		this.car = car;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
