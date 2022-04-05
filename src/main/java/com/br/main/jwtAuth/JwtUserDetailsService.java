package com.br.main.jwtAuth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.br.main.config.UserCustomDetails;
import com.br.main.models.UserSystem;
import com.br.main.services.UserService;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserSystem user = userService.getByUsername(email);
		
		if (user != null) {
			return new UserCustomDetails(user);
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}
    
}
