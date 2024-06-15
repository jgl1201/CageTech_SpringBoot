package com.cagetech.cagetech.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.EjercicioModel;

@Repository
public interface EjercicioRepository extends CrudRepository<EjercicioModel, Integer> {
    
}
