package com.lucas.rentx.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public CredenciaisDTO() {
	}

	public CredenciaisDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPssword(String password) {
		this.password = password;
	}
}
