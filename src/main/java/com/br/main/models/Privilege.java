package com.br.main.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Privilege implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="privilege_generator")
	@SequenceGenerator(name="privilege_generator", sequenceName="privilege_seq", allocationSize=1)
    private long id;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    

}
