package com.br.main.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.br.main.models.Auth;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	@DisplayName("Teste de error password invalid")
	public void passwordEmpyt() throws Exception {
		
		String err = "Password is required";
		
		Auth auth = new Auth();
		auth.setUsename("Antonio");
	
		var result = mockMvc.perform(
				post("/api/register")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(auth)))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		assertEquals(true, result.getResponse().getContentAsString().contains(err));
	}
	
	@Test
	@DisplayName("Teste de error password size invalid")
	public void passwordSizeInvalid() throws Exception {
		
		String err = "Size of password is invalid!";
		
		Auth auth = new Auth();
		auth.setUsename("Antonio");
		auth.setPassword("123");
		
		var result = mockMvc.perform(
				post("/api/register")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(auth)))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		assertEquals(true, result.getResponse().getContentAsString().contains(err));
	}
	
	@Test
	@DisplayName("Teste de error usename required")
	public void usernameEmpyt() throws Exception {
		
		String err = "Username is required";
		
		Auth auth = new Auth();
		auth.setPassword("senhavalida");
		
		var result = mockMvc.perform(
				post("/api/register")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(auth)))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		assertEquals(true, result.getResponse().getContentAsString().contains(err));
	}
	
	@Test
	@DisplayName("Teste de sucesso 1")
	public void successExample1() throws Exception {
				
		Auth auth = new Auth();
		auth.setUsename("antonio123");
		auth.setPassword("senhavalida");
		
		mockMvc.perform(
				post("/api/register")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(auth)))
		.andExpect(status().isOk());
		
	}
}
