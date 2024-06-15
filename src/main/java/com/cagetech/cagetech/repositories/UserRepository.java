package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {
    
}
