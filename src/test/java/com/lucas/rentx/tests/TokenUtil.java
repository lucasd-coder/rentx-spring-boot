package com.lucas.rentx.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.rentx.dto.CredenciaisDTO;

@Component
public class TokenUtil {
	
	@Autowired
	private ObjectMapper objectMapper;

	public String obtainAccessToken(MockMvc mockMvc, CredenciaisDTO credenciaisDTO) throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(credenciaisDTO);
				
		ResultActions result = 
				mockMvc.perform(post("/login")						
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				        .andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getHeader("Authorization");
		
		return resultString;	
	}

}
