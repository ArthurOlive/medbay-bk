package com.br.main.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_generator")
	@SequenceGenerator(name="role_generator", sequenceName="role_seq", allocationSize=1)
    private long id;
    private String name;
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Privilege> privileges;
    
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Privilege> getPrivileges() {
        return privileges;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
