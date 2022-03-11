package com.br.main.controllers.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthDTO {
	public AuthDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@NotEmpty(message = "Username is required")
	private String username;
	@NotEmpty(message = "Password is required")
	@Size(min = 6, message = "Size of password is invalid!")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
