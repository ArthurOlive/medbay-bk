package com.br.main.services;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.UserSystem;
import com.br.main.repositories.RoleRepository;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;

@Service
public class AttendantService {
    private final String role = RoleEnum.ATENDENTE.getRole().toUpperCase(Locale.ROOT);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserSystem> getAll(Pageable pageable) {
        return userRepository.findAllByRole(pageable, role);
    }

    @Transactional(readOnly = true)
    public UserSystem getById(Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("Id " + id + " not found");
        } else if (!Objects.equals(user.get().getRole().getName(), role)) {
            throw new NotFoundException("Not found Id " + id + " in this role");
        } else {
            return user.get();
        }
    }

    @Transactional
    public UserSystem create(UserSystem user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var role = roleRepository.findByName("ATENDENTE").get();
        user.getAuth().setPassword(passwordEncoder.encode(user.getAuth().getPassword()));
        user.getProfile().getMetaData().forEach(metaData -> metaData.setProfile(user.getProfile()));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Transactional
    public UserSystem update(Long id, UserSystem user) {
        UserSystem userSystem = getById(id);
        userSystem.getAuth().setUsename(user.getAuth().getUsername());

        userSystem.getProfile().setName(user.getProfile().getName());
        userSystem.getProfile().setDocument(user.getProfile().getDocument());
        userSystem.getProfile().setGender(user.getProfile().getGender());
        userSystem.getProfile().setBirth(user.getProfile().getBirth());

        userSystem.getProfile().getAddress().setCep(user.getProfile().getAddress().getCep());
        userSystem.getProfile().getAddress().setStreet(user.getProfile().getAddress().getStreet());
        userSystem.getProfile().getAddress().setNumber(user.getProfile().getAddress().getNumber());
        userSystem.getProfile().getAddress().setCity(user.getProfile().getAddress().getCity());
        userSystem.getProfile().getAddress().setCountry(user.getProfile().getAddress().getCountry());
        userSystem.getProfile().getAddress().setUf(user.getProfile().getAddress().getUf());
        return userSystem;
    }

    public void deleteById(Long id) {
        getById(id);
        userRepository.deleteById(id);
    }
}
