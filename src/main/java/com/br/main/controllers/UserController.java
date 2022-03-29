package com.br.main.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    
    @GetMapping("/api/profile")
    public String example(@AuthenticationPrincipal UserDetails userDetails) {
        return "Authenticated success!!!";
    }
}
