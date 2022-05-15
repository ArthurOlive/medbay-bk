package com.br.main.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="doctor_generator")
	@SequenceGenerator(name="doctor_generator", sequenceName="doctor_seq", allocationSize=1)
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserSystem user;
    private String crm;
    private LocalDateTime createdAt = LocalDateTime.now();

    public long getId() {
        return id;
    }
    public String getCrm() {
        return crm;
    }
    public UserSystem getUser() {
        return user;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public void setUser(UserSystem user) {
        this.user = user;
    }
    
}
