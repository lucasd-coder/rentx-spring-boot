package com.lucas.rentx.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;


public class PasswordDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String password;
		
	public PasswordDTO() {		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
