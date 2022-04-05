package com.br.main.controllers.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    
    @NotEmpty(message = "Name is required")
    public String name;
    @NotEmpty(message = "Document is required")
    public String document;
    @NotEmpty(message = "Username is required")
    public String username;
    @NotEmpty(message = "Password is required")
	@Size(min = 6, message = "Size of password is invalid!")
    public String password;
    @NotNull(message = "Role is required")
    public RoleEnum role;

    public String getDocument() {
        return document;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public RoleEnum getRole() {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(RoleEnum role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
