package com.br.main.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_generator")
	@SequenceGenerator(name="user_generator", sequenceName="user_seq", allocationSize=1)
    private long id;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Auth auth;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Profile profile;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Role role;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Auth getAuth() {
        return auth;
    }
    public long getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Profile getProfile() {
        return profile;
    }
    public Role getRole() {
        return role;
    }
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
