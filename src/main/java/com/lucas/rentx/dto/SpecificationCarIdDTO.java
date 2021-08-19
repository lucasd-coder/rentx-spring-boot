package com.lucas.rentx.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecificationCarIdDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<UUID> specification_id;

	public SpecificationCarIdDTO() {
	}

	@JsonCreator
	public SpecificationCarIdDTO(@JsonProperty("specification_id") Set<UUID> specification_id) {
		this.specification_id = specification_id;
	}

	public Set<UUID> getSpecificationId() {
		return specification_id;
	}

}