package com.br.main.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Profile implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="profile_generator")
	@SequenceGenerator(name="profile_generator", sequenceName="profile_seq", allocationSize=1)
    private long id;
    private String name;
    private LocalDate birth;
    private String document;
    private GenderEnum gender;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Address address;
    @OneToMany(mappedBy="profile", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
    private List<MetaData> medaData; 
    private LocalDateTime createdAt = LocalDateTime.now();

    public Address getAddress() {
        return address;
    }
    public String getDocument() {
        return document;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public GenderEnum getGender() {
        return gender;
    }
    public long getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public List<MetaData> getMedaData() {
        return medaData;
    }
    public String getName() {
        return name;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setMedaData(List<MetaData> medaData) {
        this.medaData = medaData;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
