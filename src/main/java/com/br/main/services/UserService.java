package com.br.main.services;

import com.br.main.models.UserSystem;
import com.br.main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserSystem getByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Transactional
    public UserSystem create(UserSystem user) {
        return userRepository.save(user);
    }
}
