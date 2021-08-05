package com.lucas.rentx.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.rentx.dto.EmailDTO;
import com.lucas.rentx.dto.PasswordDTO;
import com.lucas.rentx.security.JWTUtil;
import com.lucas.rentx.security.UserSS;
import com.lucas.rentx.services.EtherealMailService;
import com.lucas.rentx.services.ResetPasswordService;
import com.lucas.rentx.services.UserAuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private EtherealMailService service;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserAuthService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendEmail(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/reset")
	public ResponseEntity<Void> reset(@Valid @RequestBody PasswordDTO objDto,
			@RequestParam(value = "token") UUID token) {
		resetPasswordService.execute(token, objDto.getPassword());

		return ResponseEntity.noContent().build();

	}

}
