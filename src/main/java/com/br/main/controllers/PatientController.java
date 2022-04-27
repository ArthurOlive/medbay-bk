package com.br.main.controllers;

import com.br.main.controllers.dtos.PatientDTO;
import com.br.main.controllers.dtos.PatientInsertDTO;
import com.br.main.models.UserSystem;
import com.br.main.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getAllPatients(Pageable pageable) {
        Page<UserSystem> Page = patientService.getAll(pageable);
        return ResponseEntity.ok(Page.map(PatientDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(new PatientDTO(patientService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientInsertDTO patientInsertDTO) {
        UserSystem userSystem = patientService.create(PatientInsertDTO.toUserSystem(patientInsertDTO));
        return new ResponseEntity<>(new PatientDTO(userSystem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatientById(@PathVariable(value = "id") Long id, @Valid @RequestBody PatientDTO patientDTO) {
        UserSystem userSystem = patientService.update(id, PatientDTO.toUserSystem(patientDTO));
        return ResponseEntity.ok(new PatientDTO((userSystem)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable(value = "id") Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
