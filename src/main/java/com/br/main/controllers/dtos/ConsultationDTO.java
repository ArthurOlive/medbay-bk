package com.br.main.controllers.dtos;

import com.br.main.models.Consultation;
import com.br.main.models.ConsultationEnum;
import com.br.main.models.Doctor;
import com.br.main.models.UserSystem;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsultationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Doctor is required")
    private Long doctorId;

    @NotNull(message = "Patient is required")
    private Long patientId;

    @NotNull(message = "Consultation date is required")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime consultationDate;

    private Long consultationReturnId;

    private Set<String> observations = new HashSet<>();

    @NotNull(message = "Scheduled is required")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime scheduled;

    @NotNull(message = "Status is required")
    private ConsultationEnum status;

    public ConsultationDTO() {
    }

    public ConsultationDTO(Consultation consultation) {
        this.id = consultation.getId();
        this.doctorId = consultation.getDoctor().getId();
        this.patientId = consultation.getPatient().getId();
        this.consultationDate = consultation.getConsultationDate();
        if (consultation.getConsultationReturn() != null)
            this.consultationReturnId = consultation.getConsultationReturn().getId();
        this.observations = consultation.getObservations();
        this.scheduled = consultation.getScheduled();
        this.status = consultation.getStatus();
    }

    public static Consultation toConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation();
        Doctor doctor = new Doctor();
        UserSystem patient = new UserSystem();
        Consultation consultationReturn = new Consultation();

        consultation.setConsultationDate(consultationDTO.getConsultationDate());
        consultation.setObservations(consultationDTO.getObservations());
        consultation.setScheduled(consultationDTO.getScheduled());
        consultation.setStatus(consultationDTO.getStatus());

        doctor.setId(consultationDTO.getDoctorId());
        consultation.setDoctor(doctor);

        patient.setId(consultationDTO.getPatientId());
        consultation.setPatient(patient);

        if (consultationDTO.getConsultationReturnId() != null && consultationDTO.getConsultationReturnId() != 0) {
            consultationReturn.setId(consultationDTO.getConsultationReturnId());
            consultation.setConsultationReturn(consultationReturn);
        }

        return consultation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public Long getConsultationReturnId() {
        return consultationReturnId;
    }

    public void setConsultationReturnId(Long consultationReturnId) {
        this.consultationReturnId = consultationReturnId;
    }

    public Set<String> getObservations() {
        return observations;
    }

    public void setObservations(Set<String> observations) {
        this.observations = observations;
    }

    public LocalDateTime getScheduled() {
        return scheduled;
    }

    public void setScheduled(LocalDateTime scheduled) {
        this.scheduled = scheduled;
    }

    public ConsultationEnum getStatus() {
        return status;
    }

    public void setStatus(ConsultationEnum status) {
        this.status = status;
    }
}
