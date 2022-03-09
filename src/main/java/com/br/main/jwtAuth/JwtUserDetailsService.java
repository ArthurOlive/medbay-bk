package com.br.main.jwtAuth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.main.models.Auth;
import com.br.main.services.AuthService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Auth user = authService.getByUsername(email);
		
		if (user.getUsername().equals(email)) {
			return new org.springframework.security.core.userdetails.User(email, user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}
    
}
