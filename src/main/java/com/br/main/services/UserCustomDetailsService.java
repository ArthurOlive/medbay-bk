package com.br.main.services;

import java.io.Serializable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.main.models.Auth;
import com.br.main.services.dtos.UserCustomDetails;

@Component
public class UserCustomDetailsService implements UserDetailsService, Serializable {

    @Autowired
    private AuthService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Auth user = userService.getByUsername(email);
        
        return new UserCustomDetails(user);
    }
    
}
