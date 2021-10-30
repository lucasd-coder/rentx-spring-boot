package com.lucas.rentx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.rentx.dto.CarDTO;
import com.lucas.rentx.dto.CarResponseDTO;
import com.lucas.rentx.dto.CredenciaisDTO;
import com.lucas.rentx.entities.Car;
import com.lucas.rentx.services.CarService;
import com.lucas.rentx.services.exceptions.ObjectNotFoundException;
import com.lucas.rentx.tests.Factory;
import com.lucas.rentx.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarService carService;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private ObjectMapper objectMapper;

	private UUID existingId;
	private UUID nonExistingId;
	private UUID categoryId;
	private CarDTO carDTO;
	private CarDTO carDtoRequest;
	private Car car;
	private CarResponseDTO carResponseDTO;
	private PageImpl<CarResponseDTO> page;	
	private CredenciaisDTO adminAuthetication;
	private CredenciaisDTO userAuthetication;

	@BeforeEach
	void setUp() throws Exception {
		existingId = UUID.fromString("55ae7f39-0ac9-4386-a315-c07ae02ec567");
		nonExistingId = UUID.fromString("8458e545-03cb-4a68-a480-c0703745013a");
		categoryId = UUID.fromString("cf4957be-8b59-40bd-8681-7dc2a50ee570");
		carResponseDTO = Factory.createCarResponseDTO();
		car = Factory.createCar();
		page = new PageImpl<>(List.of(carResponseDTO));		
		carDTO = Factory.createCarDTO();		
		adminAuthetication = Factory.createCredenciaisDTOAdmin();
		userAuthetication = Factory.createCredenciaisDTOUser();
		carDtoRequest = Factory.createCarDTORequest();

		when(carService.listAvailable(any(), any(), any(), any())).thenReturn(page);
		when(carService.find(existingId)).thenReturn(car);
		when(carService.find(nonExistingId)).thenThrow(ObjectNotFoundException.class);

		when(carService.insert(any(), any())).thenReturn(carDTO);
	}

	@Test
	public void insertReturnCarDTOStatusCreatedWhenAdminLogged() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(carDtoRequest);
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminAuthetication);

		ResultActions result = mockMvc.perform(post("/cars/{id}", categoryId).header("Authorization", accessToken)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.description").exists());

	}

	@Test
	public void listAvailableShouldSortedPageWhenSortByName() throws Exception {
		String accessToken = tokenUtil.obtainAccessToken(mockMvc, userAuthetication);

		ResultActions result = mockMvc
				.perform(get("/cars/available?page=0&size=5&sort=name,asc")
						.header("Authorization", accessToken).accept(MediaType.APPLICATION_JSON));
		
		
		result.andExpect(status().isOk());		
		result.andExpect(jsonPath("$.content").exists());				
	}

}
