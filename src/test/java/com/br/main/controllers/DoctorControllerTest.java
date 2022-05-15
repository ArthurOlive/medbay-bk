package com.br.main.controllers;

import com.br.main.controllers.dtos.DoctorDTO;
import com.br.main.controllers.dtos.DoctorInsertDTO;
import com.br.main.models.Doctor;
import com.br.main.services.DoctorService;
import com.br.main.services.exceptions.NotFoundException;
import com.br.main.utils.DoctorFactory;
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
public class DoctorControllerTest {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService service;

    @Autowired
    private ObjectMapper objectMapper;

    private String username;
    private String password;
    private String accessToken;
    private Long existingId;
    private Long notExistingId;
    private Doctor doctor;
    private Doctor newDoctor;
    private PageImpl<Doctor> page;

    @BeforeEach
    void setUp() throws Exception {
        username = "admin@medbay.com";
        password = "senha123";
        accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        existingId = 1L;
        notExistingId = 2L;

        doctor = DoctorFactory.createDoctor();
        newDoctor = DoctorFactory.createDoctorForInsert();
        page = new PageImpl<>(List.of(doctor));

        when(service.getAll(any())).thenReturn(page);

        when(service.getById(existingId)).thenReturn(doctor);
        when(service.getById(notExistingId)).thenThrow(NotFoundException.class);

        when(service.create(any())).thenReturn(doctor);

        when(service.update(eq(existingId), any())).thenReturn(doctor);
        when(service.update(eq(notExistingId), any())).thenThrow(NotFoundException.class);

        doNothing().when(service).deleteById(existingId);
        doThrow(NotFoundException.class).when(service).deleteById(notExistingId);
    }

    @Test
    public void getAllShouldReturnDoctorsPage() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/doctors").header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void getByIdShouldReturnDoctorWhenIdExist() throws Exception {

        ResultActions result = mockMvc.perform(get("/api/doctors/{id}", existingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.crm").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void getByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(get("/doctors/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void createShouldReturnDoctorWhenCreated() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new DoctorInsertDTO(newDoctor));

        ResultActions result = mockMvc.perform(post("/api/doctors").header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.crm").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void updateShouldReturnDoctorWhenIdExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new DoctorDTO(doctor));

        ResultActions result = mockMvc.perform(put("/api/doctors/{id}", existingId).header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.username").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.birth").exists());
        result.andExpect(jsonPath("$.document").exists());
        result.andExpect(jsonPath("$.crm").exists());
        result.andExpect(jsonPath("$.gender").exists());
        result.andExpect(jsonPath("$.address").exists());
        result.andExpect(jsonPath("$.medaData").exists());

        result.andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new DoctorDTO(doctor));

        ResultActions result = mockMvc.perform(put("/api/doctors/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnNothingWhenIdExist() throws Exception {

        ResultActions result = mockMvc.perform(delete("/api/doctors/{id}", existingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(delete("/api/doctors/{id}", notExistingId).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}
