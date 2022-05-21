package com.br.main.controllers;

import com.br.main.controllers.dtos.ConsultationDTO;
import com.br.main.controllers.dtos.DoctorDTO;
import com.br.main.models.Consultation;
import com.br.main.models.Doctor;
import com.br.main.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<Page<ConsultationDTO>> getAllConsultations(Pageable pageable) {
        Page<Consultation> Page = consultationService.getAll(pageable);
        return ResponseEntity.ok(Page.map(ConsultationDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(new ConsultationDTO(consultationService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        Consultation consultation = consultationService.create(ConsultationDTO.toConsultation(consultationDTO));
        return new ResponseEntity<>(new ConsultationDTO(consultation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultationById(@PathVariable(value = "id") Long id, @Valid @RequestBody ConsultationDTO consultationDTO) {
        Consultation consultation = consultationService.update(id, ConsultationDTO.toConsultation(consultationDTO));
        return ResponseEntity.ok(new ConsultationDTO((consultation)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultationById(@PathVariable(value = "id") Long id){
        consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
