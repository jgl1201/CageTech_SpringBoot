package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.ArteMarcialModel;

@Repository
public interface ArteMarcialRepository extends CrudRepository<ArteMarcialModel, Integer> {
    
}
