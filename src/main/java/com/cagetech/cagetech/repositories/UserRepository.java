package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    
}
