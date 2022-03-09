package com.br.main.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.main.models.Auth;
import com.br.main.repositories.AuthRepository;

@Service
public class AuthService {
    
    @Autowired
    private AuthRepository authRepository;

    public Auth getByUsername(String login) {
        return authRepository.findByUsername(login);
    }

    public Auth create (Auth user) {
        return authRepository.save(user);
    }
   
}
