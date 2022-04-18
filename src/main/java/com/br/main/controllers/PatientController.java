package com.br.main.controllers;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.UserSystem;
import com.br.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final String role = RoleEnum.PACIENTE.getRole().toUpperCase(Locale.ROOT);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserSystem>> getAllPatients() {
        System.out.printf(role);
        return ResponseEntity.ok(userService.getAllByRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSystem> getPatientById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable(value = "id") Long id) {
        userService.deleteById(id, role);
        return ResponseEntity.noContent().build();
    }
}
