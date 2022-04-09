package com.br.main.repositories;

import java.util.Optional;

import com.br.main.models.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    
    public Optional<Role> findByName(String name);
}
