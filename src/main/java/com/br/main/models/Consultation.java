package com.br.main.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultation_generator")
    @SequenceGenerator(name = "consultation_generator", sequenceName = "consultation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private UserSystem patient;

    private LocalDateTime consultationDate;

    //TODO falta o atributo estabelecimento

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Consultation consultationReturn;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "observation", joinColumns = @JoinColumn(name = "consultation_id"))
    private Set<String> observations = new HashSet<>();

    private LocalDateTime scheduled;

    private ConsultationEnum status;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Consultation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public UserSystem getPatient() {
        return patient;
    }

    public void setPatient(UserSystem patient) {
        this.patient = patient;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public Consultation getConsultationReturn() {
        return consultationReturn;
    }

    public void setConsultationReturn(Consultation consultationReturn) {
        this.consultationReturn = consultationReturn;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}