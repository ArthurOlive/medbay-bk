package com.br.main.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="auth_generator")
	@SequenceGenerator(name="auth_generator", sequenceName="auth_seq", allocationSize=1)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public long setId(long id) {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsename(String username) {
        this.username = username;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
