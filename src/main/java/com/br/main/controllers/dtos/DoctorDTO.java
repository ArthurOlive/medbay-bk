package com.br.main.controllers.dtos;

import com.br.main.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Name is required")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth;

    @NotBlank(message = "Document is required")
    private String document;

    @NotBlank(message = "Crm is required")
    private String crm;

    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @NotNull(message = "Address is required")
    private Address address;

    private List<MetaData> medaData = new ArrayList<>();

    public DoctorDTO() {
    }

    public DoctorDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.username = doctor.getUser().getAuth().getUsername();
        this.name = doctor.getUser().getProfile().getName();
        this.birth = doctor.getUser().getProfile().getBirth();
        this.document = doctor.getUser().getProfile().getDocument();
        this.crm = doctor.getCrm();
        this.gender = doctor.getUser().getProfile().getGender();
        this.address = doctor.getUser().getProfile().getAddress();
        this.medaData.addAll(doctor.getUser().getProfile().getMetaData());
    }

    public static Doctor toDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        UserSystem userSystem = new UserSystem();

        userSystem.setProfile(new Profile(doctorDTO.getName(), doctorDTO.getBirth(), doctorDTO.getDocument(), doctorDTO.getGender(), doctorDTO.getAddress(), doctorDTO.getMedaData()));
        userSystem.setAuth(new Auth(doctorDTO.getUsername()));
        doctor.setUser(userSystem);
        doctor.setCrm(doctorDTO.getCrm());

        return doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MetaData> getMedaData() {
        return medaData;
    }

    public void setMedaData(List<MetaData> medaData) {
        this.medaData = medaData;
    }
}
