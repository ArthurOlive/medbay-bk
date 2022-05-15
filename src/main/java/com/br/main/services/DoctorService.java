package com.br.main.services;

import com.br.main.models.Doctor;
import com.br.main.models.Role;
import com.br.main.models.UserSystem;
import com.br.main.repositories.DoctorRepository;
import com.br.main.repositories.RoleRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<Doctor> getAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Doctor getById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        if (doctor.isEmpty()) {
            throw new NotFoundException("Id " + id + " not found");
        } else {
            return doctor.get();
        }
    }

    @Transactional
    public Doctor create(Doctor doctor) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role = roleRepository.findByName("MEDICO").get();
        UserSystem userSystem = doctor.getUser();

        userSystem.getAuth().setPassword(passwordEncoder.encode(doctor.getUser().getAuth().getPassword()));
        userSystem.getProfile().getMetaData().forEach(metaData -> metaData.setProfile(doctor.getUser().getProfile()));
        userSystem.setRole(role);
        doctor.setUser(userSystem);

        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(Long id, Doctor doctor) {
        Doctor entity = getById(id);
        entity.getUser().getAuth().setUsename(doctor.getUser().getAuth().getUsername());

        entity.getUser().getProfile().setName(doctor.getUser().getProfile().getName());
        entity.getUser().getProfile().setDocument(doctor.getUser().getProfile().getDocument());
        entity.getUser().getProfile().setGender(doctor.getUser().getProfile().getGender());
        entity.getUser().getProfile().setBirth(doctor.getUser().getProfile().getBirth());

        entity.getUser().getProfile().getAddress().setCep(doctor.getUser().getProfile().getAddress().getCep());
        entity.getUser().getProfile().getAddress().setStreet(doctor.getUser().getProfile().getAddress().getStreet());
        entity.getUser().getProfile().getAddress().setNumber(doctor.getUser().getProfile().getAddress().getNumber());
        entity.getUser().getProfile().getAddress().setCity(doctor.getUser().getProfile().getAddress().getCity());
        entity.getUser().getProfile().getAddress().setCountry(doctor.getUser().getProfile().getAddress().getCountry());
        entity.getUser().getProfile().getAddress().setUf(doctor.getUser().getProfile().getAddress().getUf());

        entity.setCrm(doctor.getCrm());

        return entity;
    }

    public void deleteById(Long id) {
        getById(id);
        doctorRepository.deleteById(id);
    }
}
