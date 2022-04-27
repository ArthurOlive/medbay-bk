package com.br.main.controllers.dtos;

import com.br.main.models.Auth;
import com.br.main.models.Profile;
import com.br.main.models.UserSystem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PatientInsertDTO extends PatientDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Size of password is invalid!")
    private String password;

    public PatientInsertDTO() {
        super();
    }

    public static UserSystem toUserSystem(PatientInsertDTO patientInsertDTO) {
        UserSystem userSystem = new UserSystem();

        userSystem.setProfile(new Profile(patientInsertDTO.getName(), patientInsertDTO.getBirth(), patientInsertDTO.getDocument(), patientInsertDTO.getGender(), patientInsertDTO.getAddress(), patientInsertDTO.getMedaData()));
        userSystem.setRole(patientInsertDTO.getRole());
        userSystem.setAuth(new Auth(patientInsertDTO.getUsername(), patientInsertDTO.getPassword()));

        return userSystem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}