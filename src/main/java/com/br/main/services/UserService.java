package com.br.main.services;

import com.br.main.models.UserSystem;
import com.br.main.repositories.UserRepository;
import com.br.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserSystem> getAllByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    public UserSystem getById(Long id, String role) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("Id " + id + " not found");
        } else if (!Objects.equals(user.get().getRole().getName(), role)) {
            throw new NotFoundException("Not found Id " + id + " in this role");
        } else {
            return user.get();
        }
    }

    public UserSystem getByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Transactional
    public UserSystem create(UserSystem user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id, String role) {
        getById(id, role);
        userRepository.deleteById(id);
    }
}
