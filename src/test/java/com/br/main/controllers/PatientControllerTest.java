package com.br.main.controllers;

import com.br.main.controllers.dtos.PatientDTO;
import com.br.main.controllers.dtos.PatientInsertDTO;
import com.br.main.models.Role;
import com.br.main.models.UserSystem;
import com.br.main.services.PatientService;
import com.br.main.services.exceptions.NotFoundException;
import com.br.main.utils.PatientFactory;
import com.br.main.utils.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @Autowired
    private ObjectMapper objectMapper;

    private String username;
    private String password;
    private String accessToken;
    private Long existingId;
    private Long notExistingId;
    private Long notPatientId;
    private UserSystem patient;
    private UserSystem newPatient;
    private UserSystem notPatient;
    private PageImpl<UserSystem> page;

    @BeforeEach
    void setUp() throws Exception {
        username = "admin@medbay.com";
        password = "senha123";
        accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        existingId = 1L;
        notExistingId = 2L;
        notPatientId = 3L;

        patient = PatientFactory.createPatient();
        newPatient = PatientFactory.createPatientForInsert();
        notPatient = PatientFactory.createPatient();
        notPatient.setRole(new Role());
        page = new PageImpl<>(List.of(patient));

        when(service.getAll(any())).thenReturn(page);

        when(service.getById(existingId)).thenReturn(patient);
        when(service.getById(notExistingId)).thenThrow(NotFoundException.class);
        when(service.getById(notPatientId)).thenReturn(notPatient);

        when(service.create(any())).thenReturn(patient);

        when(service.update(eq(existingId), any())).thenReturn(patient);
        when(service.update(eq(notExistingId), any())).thenThrow(NotFoundException.class);

        doNothing().when(service).deleteById(existingId);
        doThrow(NotFoundException.class).when(service).deleteById(notExistingId);
    }

    @Test
    public void getAllShouldReturnPatientsPage() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/patients").header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void getByIdShouldReturnPatientWhenIdExist() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/patients/{id}", existingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.role").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void getByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void getByIdShouldReturnFoundWhenIdDoesNotAPatient() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", notPatientId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void createShouldReturnPatientWhenCreated() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new PatientInsertDTO(newPatient));

        ResultActions result = mockMvc.perform(post("/api/patients").header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.role").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void updateShouldReturnPatientWhenIdExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new PatientDTO(patient));

        ResultActions result = mockMvc.perform(put("/api/patients/{id}", existingId).header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.role").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new PatientDTO(patient));

        ResultActions result = mockMvc.perform(put("/api/patients/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnNothingWhenIdExist() throws Exception {

        ResultActions result = mockMvc.perform(delete("/api/patients/{id}", existingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(delete("/api/patients/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}
