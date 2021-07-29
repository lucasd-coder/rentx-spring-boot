package com.lucas.rentx.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lucas.rentx.security.UserSS;

public class UserAuthService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		} catch (Exception e) {
			return null;
		}
	}

}
