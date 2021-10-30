package com.lucas.rentx.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.rentx.dto.CredenciaisDTO;
import com.lucas.rentx.entities.User;
import com.lucas.rentx.services.UserService;
import com.lucas.rentx.services.exceptions.AuthorizationException;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;
import com.lucas.rentx.tests.Factory;
import com.lucas.rentx.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIT {

	@Autowired
	private MockMvc mcMvc;
	
	@Autowired
	private TokenUtil tokenUtil;

	@MockBean
	private UserService userService;	

	private UUID existingId;
	private UUID nonExistingId;
	private UUID invalidId;
	private User user;
	private CredenciaisDTO userAuthetication;

	@BeforeEach
	void setUp() throws Exception {

		existingId = UUID.fromString("420ee4ba-6eec-4f14-a237-14cc972d0494");
		nonExistingId = UUID.fromString("8458e545-03cb-4a68-a480-c0703745013a");
		invalidId = null;
		user = Factory.createUser();
		userAuthetication = Factory.createCredenciaisDTOUser();

		when(userService.find(existingId)).thenReturn(user);
		when(userService.find(nonExistingId)).thenThrow(ObjectNotFoundException.class);
		when(userService.find(invalidId)).thenThrow(AuthorizationException.class);
	}

	@Test	
	public void findShouldReturnUserResponseDTOWhenIdExists() throws Exception {
		String accessToken = tokenUtil.obtainAccessToken(mcMvc, userAuthetication);
		ResultActions result = mcMvc.perform(
				get("/users/{id}", existingId)
				.header("Authorization", accessToken)				
				.contentType(MediaType.APPLICATION_JSON));
		
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}
	@Test	
	public void findShouldReturnUnauthorizedWhenIdDoesNotExists() throws Exception {
		ResultActions result = mcMvc.perform(get("/users/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isForbidden());		
	}
}
