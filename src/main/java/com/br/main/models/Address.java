package com.br.main.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_generator")
	@SequenceGenerator(name="address_generator", sequenceName="address_seq", allocationSize=1)
    private long id;
    private String cep;
    private String country;
    private String uf;
    private String city;
    private String street;
    private String number;
    private String complement;

    public String getCep() {
        return cep;
    }
    public String getCity() {
        return city;
    }
    public String getComplement() {
        return complement;
    }
    public String getCountry() {
        return country;
    }
    public long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public String getStreet() {
        return street;
    }
    public String getUf() {
        return uf;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setComplement(String complement) {
        this.complement = complement;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

}
