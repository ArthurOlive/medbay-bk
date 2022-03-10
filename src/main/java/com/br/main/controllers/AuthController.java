package com.br.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.main.controllers.dtos.AuthDTO;
import com.br.main.jwtAuth.JwtTokenUtil;
import com.br.main.jwtAuth.JwtUserDetailsService;
import com.br.main.models.Auth;
import com.br.main.services.AuthService;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired 
    private JwtUserDetailsService userDetailsService;
	
    @Autowired
    private BCryptPasswordEncoder encode;
    
    @Autowired
    private AuthService authService;
    
    @Autowired 
    private AuthenticationManager authenticationManager;
	
	@PostMapping("/api/register")
	public String register(@RequestBody AuthDTO authDTO) {
		
        Auth user = new Auth();

        user.setUsename(authDTO.getUsername());
        user.setPassword(encode.encode(authDTO.getPassword()));

        authService.create(user);
        		
		return "Usuario criado com sucesso!";
	}
	
	@PostMapping("/api/authenticate")
	public String authentiacate(@RequestBody AuthDTO authDTO) throws Exception {
		
		authenticate(authDTO.getUsername(), authDTO.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        		
		return token;
	}
	
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
        	throw new Exception(e);
        }
    }

}
