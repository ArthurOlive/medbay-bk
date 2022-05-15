package com.br.main.controllers;

import com.br.main.controllers.dtos.DoctorDTO;
import com.br.main.controllers.dtos.DoctorInsertDTO;
import com.br.main.models.Doctor;
import com.br.main.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> getAllDoctors(Pageable pageable) {
        Page<Doctor> Page = doctorService.getAll(pageable);
        return ResponseEntity.ok(Page.map(DoctorDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(new DoctorDTO(doctorService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorInsertDTO doctorInsertDTO) {
        Doctor doctor = doctorService.create(DoctorInsertDTO.toDoctor(doctorInsertDTO));
        return new ResponseEntity<>(new DoctorDTO(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctorById(@PathVariable(value = "id") Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.update(id, DoctorDTO.toDoctor(doctorDTO));
        return ResponseEntity.ok(new DoctorDTO((doctor)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable(value = "id") Long id) {
        doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
