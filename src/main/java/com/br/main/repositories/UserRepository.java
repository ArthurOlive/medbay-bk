package com.br.main.repositories;

import com.br.main.models.UserSystem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserSystem, Long> {

    @Query("SELECT u FROM UserSystem u WHERE u.auth.username =:username")
    public UserSystem findByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT u FROM UserSystem u WHERE u.role.name =:role")
    Page<UserSystem> findAllByRole(Pageable pageable, String role);
}
