package com.br.main.controllers;

import java.util.ArrayList;
import java.util.List;

import com.br.main.config.UserCustomDetails;
import com.br.main.models.UserSystem;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    
    @GetMapping("/api/doctors")
    public List<UserSystem> getDoctors(@AuthenticationPrincipal UserCustomDetails userDetails) {

        return new ArrayList<UserSystem>();
    }

}
