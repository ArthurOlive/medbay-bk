package com.br.main.controllers.dtos;

import com.br.main.models.Auth;
import com.br.main.models.Doctor;
import com.br.main.models.Profile;
import com.br.main.models.UserSystem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DoctorInsertDTO extends DoctorDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Size of password is invalid!")
    private String password;

    public DoctorInsertDTO() {
        super();
    }

    public DoctorInsertDTO(Doctor doctor) {
        super(doctor);
        this.setPassword(doctor.getUser().getAuth().getPassword());
    }

    public static Doctor toDoctor(DoctorInsertDTO doctorInsertDTO) {
        Doctor doctor = new Doctor();
        UserSystem userSystem = new UserSystem();

        userSystem.setProfile(new Profile(doctorInsertDTO.getName(), doctorInsertDTO.getBirth(), doctorInsertDTO.getDocument(), doctorInsertDTO.getGender(), doctorInsertDTO.getAddress(), doctorInsertDTO.getMedaData()));
        userSystem.setAuth(new Auth(doctorInsertDTO.getUsername(), doctorInsertDTO.getPassword()));
        doctor.setUser(userSystem);
        doctor.setCrm(doctorInsertDTO.getCrm());

        return doctor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}