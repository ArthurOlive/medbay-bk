package com.br.main.services;

import com.br.main.models.Doctor;
import com.br.main.models.Role;
import com.br.main.repositories.DoctorRepository;
import com.br.main.repositories.RoleRepository;
import com.br.main.services.exceptions.NotFoundException;
import com.br.main.utils.DoctorFactory;
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
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService service;

    @Mock
    private DoctorRepository repository;

    @Mock
    private RoleRepository roleRepository;

    private PageImpl<Doctor> page;
    private Long existingId;
    private Long notExistingId;
    private Doctor doctor;
    private Doctor newDoctor;
    private Role role;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        notExistingId = 2L;
        doctor = DoctorFactory.createDoctor();
        newDoctor = DoctorFactory.createDoctorForInsert();
        page = new PageImpl<>(List.of(doctor));
        role = DoctorFactory.createDoctorRole();

        when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        when(repository.findById(existingId)).thenReturn(Optional.of(doctor));
        when(repository.findById(notExistingId)).thenReturn(Optional.empty());

        when(repository.save(ArgumentMatchers.any())).thenReturn(doctor);
        when(roleRepository.findByName(ArgumentMatchers.any())).thenReturn(Optional.of(role));

        doNothing().when(repository).deleteById(existingId);
    }

    @Test
    @DisplayName("Get all doctors valid page")
    public void getAllShouldReturnDoctorsPage() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Doctor> result = service.getAll(pageable);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Get doctor existing by id")
    public void getByIdShouldReturnDoctorWhenIdExist() {
        Doctor result = service.getById(existingId);

        assertNotNull(result);
        verify(repository).findById(existingId);
    }

    @Test
    @DisplayName("Error get doctor not existing by id")
    public void getByIdShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.getById(notExistingId);
        });
    }

    @Test
    @DisplayName("Create new doctor")
    public void createShouldReturnDoctor() {

        Doctor result = service.create(newDoctor);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Update doctor existing")
    public void updateShouldReturnDoctorWhenIdExist() {

        Doctor result = service.update(existingId, doctor);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Error update doctor not existing by id")
    public void updateShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.update(notExistingId, doctor);
        });
    }

    @Test
    @DisplayName("Delete doctor existing by id")
    public void deleteShouldReturnNothingWhenIdExist() {

        assertDoesNotThrow(() -> {
            service.deleteById(existingId);
        });

        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    @DisplayName("Error delete doctor not existing by id")
    public void deleteShouldThrowNotFoundExceptionWhenIdDoesNotExist() {

        assertThrows(NotFoundException.class, () -> {
            service.deleteById(notExistingId);
        });
    }
}
