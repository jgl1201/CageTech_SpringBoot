package com.cagetech.cagetech.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cagetech.cagetech.models.RutinaEjercicioId;
import com.cagetech.cagetech.models.RutinaEjercicioModel;

@Repository
public interface RutinaEjercicioRepository extends CrudRepository<RutinaEjercicioModel, RutinaEjercicioId> {

    List<RutinaEjercicioModel> findByRutinaId(Integer rutinaId);

} 