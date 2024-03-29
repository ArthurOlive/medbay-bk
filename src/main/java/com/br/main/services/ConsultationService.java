package com.br.main.services;

import com.br.main.config.UserCustomDetails;
import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.Consultation;
import com.br.main.models.Doctor;
import com.br.main.models.UserSystem;
import com.br.main.repositories.ConsultationRepository;
import com.br.main.repositories.DoctorRepository;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Consultation> getAll(Pageable pageable, UserCustomDetails userDetails) {
        if (userDetails.getRole().equals(RoleEnum.MEDICO.toString().toUpperCase())) {
            var profile = userDetails.getUser().getProfile();
            var userId = userRepository.findByProfileId(profile.getId()).getId();
            return consultationRepository.findAllByDoctor(pageable, userId);
        } else if (userDetails.getRole().equals(RoleEnum.PACIENTE.toString().toUpperCase())) {
            var profile = userDetails.getUser().getProfile();
            var userId = userRepository.findByProfileId(profile.getId()).getId();
            return consultationRepository.findAllByPaciente(pageable, userId);
        } else
            return consultationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Consultation getById(Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);

        if (consultation.isEmpty()) {
            throw new NotFoundException("Id " + id + " not found");
        } else {
            return consultation.get();
        }
    }

    @Transactional
    public Consultation create(Consultation consultation) {
        if (consultationRepository.findByScheduled(consultation.getScheduled()).isPresent())
            throw new DataIntegrityViolationException("This schedule already exists");

        return consultationRepository.save(checkConsultation(consultation));
    }

    @Transactional
    public Consultation update(Long id, Consultation consultation) {
        Consultation entity = getById(id);
        consultation = checkConsultation(consultation);

        entity.setDoctor(consultation.getDoctor());
        entity.setPatient(consultation.getPatient());
        entity.setConsultationReturn(consultation.getConsultationReturn());
        entity.setConsultationDate(consultation.getConsultationDate());
        entity.setObservations(consultation.getObservations());
        entity.setScheduled(consultation.getScheduled());
        entity.setStatus(consultation.getStatus());

        return entity;
    }

    private Consultation checkConsultation(Consultation consultation) {
        Optional<Doctor> doctor = doctorRepository.findById(consultation.getDoctor().getId());
        Optional<UserSystem> patient = userRepository.findById(consultation.getPatient().getId());
        Optional<Consultation> consultationReturn = Optional.empty();

        if (consultation.getConsultationReturn() != null)
            consultationReturn = consultationRepository.findById(consultation.getConsultationReturn().getId());

        if (doctor.isEmpty()) {
            throw new NotFoundException("Doctor with id " + consultation.getDoctor().getId() + " not found");
        } else if (patient.isEmpty() || !patient.get().getRole().getName().equals(RoleEnum.PACIENTE.name())) {
            throw new NotFoundException("Patient with id " + consultation.getPatient().getId() + " not found");
        }
        if (consultation.getConsultationReturn() != null)
            if (consultationReturn.isEmpty()) {
                throw new NotFoundException(
                        "Consultation return with id " + consultation.getConsultationReturn().getId() + " not found");
            }

        consultation.setDoctor(doctor.get());
        consultation.setPatient(patient.get());

        if (consultation.getConsultationReturn() != null)
            consultation.setConsultationReturn(consultationReturn.get());

        return consultation;
    }

    public void deleteById(Long id) {
        getById(id);

        try {
            consultationRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }
}
