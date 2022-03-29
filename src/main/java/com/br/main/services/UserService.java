package com.br.main.services;

import com.br.main.models.User;
import com.br.main.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User getByUsername(String login) {
        return userRepository.findByUsername(login);
    }
}
