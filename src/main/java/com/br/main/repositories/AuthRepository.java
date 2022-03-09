package com.br.main.repositories;

import org.springframework.stereotype.Repository;

import com.br.main.models.Auth;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface AuthRepository extends CrudRepository<Auth, Long>{

    public Auth findByUsername(String login);

}

