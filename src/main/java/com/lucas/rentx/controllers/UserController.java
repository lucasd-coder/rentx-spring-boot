package com.lucas.rentx.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.rentx.dto.UserDTO;
import com.lucas.rentx.dto.UserResponseDTO;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> find(@PathVariable UUID id) {
		User obj = userService.find(id);
		UserResponseDTO userDto = new UserResponseDTO(obj);
		BeanUtils.copyProperties(obj, userDto);
		return ResponseEntity.ok().body(userDto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO objDto) {
		objDto = userService.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping(value = "/avatar")
	public ResponseEntity<Void> uploadUserAvatar(@RequestParam MultipartFile file) {
		userService.uploadAvatar(file);
		return ResponseEntity.ok().build();
	}

}
