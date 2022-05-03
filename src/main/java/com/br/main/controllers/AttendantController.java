package com.br.main.controllers;

import com.br.main.controllers.dtos.AttendantDTO;
import com.br.main.controllers.dtos.AttendantInsertDTO;
import com.br.main.models.UserSystem;
import com.br.main.services.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/attendants")
public class AttendantController {

    @Autowired
    private AttendantService attendantService;

    @GetMapping
    public ResponseEntity<Page<AttendantDTO>> getAllAttendants(Pageable pageable) {
        Page<UserSystem> Page = attendantService.getAll(pageable);
        return ResponseEntity.ok(Page.map(AttendantDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendantDTO> getAttendantById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(new AttendantDTO(attendantService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<AttendantDTO> createAttendant(@Valid @RequestBody AttendantInsertDTO attendantInsertDTO) {
        UserSystem userSystem = attendantService.create(AttendantInsertDTO.toUserSystem(attendantInsertDTO));
        return new ResponseEntity<>(new AttendantDTO(userSystem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendantDTO> updateAttendantById(@PathVariable(value = "id") Long id, @Valid @RequestBody AttendantDTO attendantDTO) {
        UserSystem userSystem = attendantService.update(id, AttendantDTO.toUserSystem(attendantDTO));
        return ResponseEntity.ok(new AttendantDTO((userSystem)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendantById(@PathVariable(value = "id") Long id) {
        attendantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
