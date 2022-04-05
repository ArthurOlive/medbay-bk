package com.br.main.services;

import java.sql.SQLException;

import javax.transaction.Transactional;

import com.br.main.models.UserSystem;
import com.br.main.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public UserSystem getByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Transactional
    public UserSystem create(UserSystem user) throws SQLException {
        return userRepository.save(user);
    } 
}
