package com.br.main.models;

import java.io.Serializable;

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

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
