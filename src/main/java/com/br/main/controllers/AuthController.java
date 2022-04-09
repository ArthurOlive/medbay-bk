package com.br.main.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.jboss.logging.Logger;
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

import com.br.main.config.UserCustomDetails;
import com.br.main.controllers.dtos.AuthDTO;
import com.br.main.controllers.dtos.UserRegisterDTO;
import com.br.main.jwtAuth.JwtTokenUtil;
import com.br.main.jwtAuth.JwtUserDetailsService;
import com.br.main.models.Auth;
import com.br.main.models.Profile;
import com.br.main.models.Role;
import com.br.main.models.UserSystem;
import com.br.main.services.AuthService;
import com.br.main.services.DocumentValidatator;
import com.br.main.services.RoleService;
import com.br.main.services.UserService;

@CrossOrigin
@RestController
public class AuthController {

    Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired 
    private JwtUserDetailsService userDetailsService;
	
    @Autowired
    private BCryptPasswordEncoder encode;

    @Autowired
    private UserService userService;
    
    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;

	
	@PostMapping("/api/register")
	public String register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) throws Exception {

        UserSystem user = new UserSystem();

        user.setAuth(new Auth());
        user.setProfile(new Profile());

        user.getAuth().setUsename(userRegisterDTO.getUsername());
        user.getAuth().setPassword(encode.encode(userRegisterDTO.getPassword()));

        user.getProfile().setName(userRegisterDTO.getName());

        String document = userRegisterDTO.getDocument();
        document = document.replace(".", "");
        document = document.replace("-", "");
        if (!DocumentValidatator.isCPF(document) && !DocumentValidatator.isCNPJ(document)) {
            throw new Exception("Documento inválido!");
        }

        user.getProfile().setDocument(userRegisterDTO.getDocument());
        
        Role role = roleService.findByRoleEnum(userRegisterDTO.getRole()).orElseThrow();

        user.setRole(role);

        try {
            userService.create(user);
        } catch (Exception e) {
            throw new Exception("Não foi possivel salvar o usuario!");
        }
        		
		return "Usuario criado com sucesso!";
	}
	
	@PostMapping("/api/authenticate")
	public String authentiacate(@Valid @RequestBody AuthDTO authDTO) throws Exception {
		
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
