package com.br.main.repositories;

import com.br.main.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.auth.username =:username")
    public User findByUsername(@Param("username") String username);

}
