package com.br.main.controllers.dtos;

import com.br.main.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDTO implements Serializable {
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

    @NotNull(message = "Gender is required")
    private GenderEnum gender;

    @NotNull(message = "Address is required")
    private Address address;

    @NotNull(message = "Role is required")
    private Role role;

    private List<MetaData> medaData = new ArrayList<>();

    public PatientDTO() {
    }

    public PatientDTO(UserSystem userSystem) {
        this.id = userSystem.getId();
        this.username = userSystem.getAuth().getUsername();
        this.name = userSystem.getProfile().getName();
        this.birth = userSystem.getProfile().getBirth();
        this.document = userSystem.getProfile().getDocument();
        this.gender = userSystem.getProfile().getGender();
        this.address = userSystem.getProfile().getAddress();
        this.role = userSystem.getRole();
        this.medaData.addAll(userSystem.getProfile().getMetaData());
    }

    public static UserSystem toUserSystem(PatientDTO patientDTO) {
        UserSystem userSystem = new UserSystem();

        userSystem.setAuth(new Auth(patientDTO.getUsername()));
        userSystem.setProfile(new Profile(patientDTO.getName(),
                patientDTO.getBirth(),
                patientDTO.getDocument(),
                patientDTO.getGender(),
                patientDTO.getAddress(),
                patientDTO.getMedaData()));
        userSystem.setRole(patientDTO.getRole());

        return userSystem;
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

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<MetaData> getMedaData() {
        return medaData;
    }

    public void setMedaData(List<MetaData> medaData) {
        this.medaData = medaData;
    }
}
