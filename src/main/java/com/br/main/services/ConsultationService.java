package com.br.main.services;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.Consultation;
import com.br.main.models.Doctor;
import com.br.main.models.UserSystem;
import com.br.main.repositories.ConsultationRepository;
import com.br.main.repositories.DoctorRepository;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Page<Consultation> getAll(Pageable pageable) {
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
                throw new NotFoundException("Consultation return with id " + consultation.getConsultationReturn().getId() + " not found");
            }

        consultation.setDoctor(doctor.get());
        consultation.setPatient(patient.get());

        if (consultation.getConsultationReturn() != null)
            consultation.setConsultationReturn(consultationReturn.get());

        return consultationRepository.save(consultation);
    }
}
