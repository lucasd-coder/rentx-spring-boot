package com.lucas.rentx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalCustomBodyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID car_id;

	private Date expected_return_date;

	public RentalCustomBodyDTO() {
	}

	@JsonCreator
	public RentalCustomBodyDTO(@JsonProperty("car_id") UUID car_id,
			@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") Date expected_return_date) {
		this.car_id = car_id;
		this.expected_return_date = expected_return_date;
	}

	public UUID getCarId() {
		return car_id;
	}

	public Date getExpectedReturnDate() {
		return expected_return_date;
	}

}