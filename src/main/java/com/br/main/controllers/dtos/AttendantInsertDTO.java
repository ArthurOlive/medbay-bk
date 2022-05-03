package com.br.main.controllers.dtos;

import com.br.main.models.Auth;
import com.br.main.models.Profile;
import com.br.main.models.UserSystem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AttendantInsertDTO extends AttendantDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Size of password is invalid!")
    private String password;

    public AttendantInsertDTO() {
        super();
    }

    public AttendantInsertDTO(UserSystem userSystem) {
        super(userSystem);
        this.setPassword(userSystem.getAuth().getPassword());
    }

    public static UserSystem toUserSystem(AttendantInsertDTO attendantInsertDTO) {
        UserSystem userSystem = new UserSystem();

        userSystem.setProfile(new Profile(attendantInsertDTO.getName(), attendantInsertDTO.getBirth(), attendantInsertDTO.getDocument(), attendantInsertDTO.getGender(), attendantInsertDTO.getAddress(), attendantInsertDTO.getMedaData()));
        userSystem.setRole(attendantInsertDTO.getRole());
        userSystem.setAuth(new Auth(attendantInsertDTO.getUsername(), attendantInsertDTO.getPassword()));

        return userSystem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}