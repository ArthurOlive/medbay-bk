package com.br.main.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Enteprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="enteprise_generator")
	@SequenceGenerator(name="enteprise_generator", sequenceName="enteprise_seq", allocationSize=1)
    private long id;
    private String name;
    private String cnpj;
    @OneToOne
    private Address address;
    @ManyToMany
    private List<UserSystem> patients;
    @ManyToMany
    private List<UserSystem> employees;
    @ManyToMany
    private List<Doctor> doctors;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Address getAddress() {
        return address;
    }
    public String getCnpj() {
        return cnpj;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public List<Doctor> getDoctors() {
        return doctors;
    }
    public List<UserSystem> getEmployees() {
        return employees;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<UserSystem> getPatients() {
        return patients;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    public void setEmployees(List<UserSystem> employees) {
        this.employees = employees;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPatients(List<UserSystem> patients) {
        this.patients = patients;
    }

}
