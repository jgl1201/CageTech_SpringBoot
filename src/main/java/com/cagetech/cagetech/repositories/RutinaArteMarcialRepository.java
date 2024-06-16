package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.RutinaArteMarcialModel;

@Repository
public interface RutinaArteMarcialRepository extends CrudRepository<RutinaArteMarcialModel, Integer>{
    
}
