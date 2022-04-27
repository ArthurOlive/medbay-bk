package com.br.main.services;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.UserSystem;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;

@Service
public class PatientService {
    private final String role = RoleEnum.PACIENTE.getRole().toUpperCase(Locale.ROOT);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
        user.getAuth().setPassword(passwordEncoder.encode(user.getAuth().getPassword()));
        user.getProfile().getMetaData().forEach(metaData -> metaData.setProfile(user.getProfile()));
        return userRepository.save(user);
    }

    @Transactional
    public UserSystem update(Long id, UserSystem user) {
        UserSystem userSystem = getById(id);
        user.setId(userSystem.getId());
        user.getProfile().setId(userSystem.getProfile().getId());
        user.getAuth().setId(userSystem.getAuth().getId());
        user.getProfile().getMetaData().forEach(metaData -> metaData.setProfile(user.getProfile()));
        BeanUtils.copyProperties(user, userSystem);
        return userRepository.save(userSystem);
    }

    public void deleteById(Long id) {
        getById(id);
        userRepository.deleteById(id);
    }
}
