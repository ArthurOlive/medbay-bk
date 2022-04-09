package com.br.main.services;

import java.util.Optional;

import com.br.main.controllers.dtos.RoleEnum;
import com.br.main.models.Role;
import com.br.main.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    public Optional<Role> findByRoleEnum(RoleEnum role) {
        
        if (role.equals(RoleEnum.MEDICO)) {
            return roleRepository.findByName("MEDICO");
        } 
        if (role.equals(RoleEnum.ATENDENTE)) {
            return roleRepository.findByName("ATENDENTE");
        }
        if (role.equals(RoleEnum.PACIENTE)) {
            return roleRepository.findByName("PACIENTE");
        }

        return null;
    }
}
