package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.RutinaArteMarcialModel;
import com.cagetech.cagetech.models.RutinaArtemarcialId;

@Repository
public interface RutinaArteMarcialRepository extends CrudRepository<RutinaArteMarcialModel, RutinaArtemarcialId>{
    
}
