package com.br.main.services;

import com.br.main.models.Role;
import com.br.main.models.UserSystem;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import com.br.main.utils.PatientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService service;

    @Mock
    private UserRepository repository;

    private PageImpl<UserSystem> page;
    private Long existingId;
    private Long notExistingId;
    private Long notPatientId;
    private UserSystem patient;
    private UserSystem newPatient;
    private UserSystem notPatient;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        notExistingId = 2L;
        notPatientId = 3L;
        patient = PatientFactory.createPatient();
        newPatient = PatientFactory.createPatientForInsert();
        notPatient = PatientFactory.createPatient();
        notPatient.setRole(new Role());
        page = new PageImpl<>(List.of(patient));

        when(repository.findAllByRole((Pageable) ArgumentMatchers.any(), any())).thenReturn(page);

        when(repository.findById(existingId)).thenReturn(Optional.of(patient));
        when(repository.findById(notExistingId)).thenReturn(Optional.empty());
        when(repository.findById(notPatientId)).thenReturn(Optional.of(notPatient));

        when(repository.save(ArgumentMatchers.any())).thenReturn(patient);

        doNothing().when(repository).deleteById(existingId);
    }

    @Test
    @DisplayName("Get all patients valid page")
    public void getAllShouldReturnPatientsPage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<UserSystem> result = service.getAll(pageable);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Get patient existing by id")
    public void getByIdShouldReturnPatientWhenIdExist() {
        UserSystem result = service.getById(existingId);

        assertNotNull(result);
        verify(repository).findById(existingId);
    }

    @Test
    @DisplayName("Error get patient not existing by id")
    public void getByIdShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.getById(notExistingId);
        });
    }

    @Test
    @DisplayName("Error get patient with invalid role")
    public void getByIdShouldThrowNotFoundExceptionWhenIdDoesNotAPatient() {

        assertThrows(NotFoundException.class, () -> {
            service.getById(notPatientId);
        });
    }

    @Test
    @DisplayName("Create new patient")
    public void createShouldReturnPatient() {

        UserSystem result = service.create(newPatient);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Update patient existing")
    public void updateShouldReturnPatientWhenIdExist() {

        UserSystem result = service.update(existingId, patient);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Error update patient not existing by id")
    public void updateShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.update(notExistingId, patient);
        });
    }

    @Test
    @DisplayName("Delete patient existing by id")
    public void deleteShouldReturnNothingWhenIdExist() {

        assertDoesNotThrow(() -> {
            service.deleteById(existingId);
        });

        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    @DisplayName("Error delete patient not existing by id")
    public void deleteShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.deleteById(notExistingId);
        });
    }
}
