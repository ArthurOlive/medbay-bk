package com.br.main.controllers;

import com.br.main.controllers.dtos.UserRegisterDTO;
import com.br.main.utils.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Invalid name test")
    public void nameEmpty() throws Exception {

        UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setName("");

        String err = "Name is required";

        var result = mockMvc.perform(
                        post("/api/register")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(userRegisterDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
    }

	@Test
	@DisplayName("Invalid document test")
	public void documentEmpty() throws Exception {

		UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setDocument("");

		String err = "Document is required";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
	}

	@Test
	@DisplayName("Invalid username test")
	public void usernameEmpty() throws Exception {

		UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setUsername("");

		String err = "Username is required";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
	}

	@Test
	@DisplayName("Invalid password test")
	public void passwordEmpty() throws Exception {

		UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setPassword("");

		String err = "Password is required";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
	}

	@Test
	@DisplayName("Invalid document test")
	public void roleInvalid() throws Exception {

		UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setRole(null);

		String err = "Role is required";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
	}

	@Test
	@DisplayName("Invalid password size test")
	public void passwordSizeInvalid() throws Exception {

		UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();
		userRegisterDTO.setPassword("123");

		String err = "Size of password is invalid!";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(err));
	}

    @Test
    @DisplayName("Success test")
    public void successExample1() throws Exception {

        UserRegisterDTO userRegisterDTO = Factory.createUserRegisterDTO();

		String success = "Usuario criado com sucesso!";

		var result = mockMvc.perform(
						post("/api/register")
								.contentType("application/json")
								.content(objectMapper.writeValueAsString(userRegisterDTO)))
				.andExpect(status().isOk())
				.andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(success));
    }
}
