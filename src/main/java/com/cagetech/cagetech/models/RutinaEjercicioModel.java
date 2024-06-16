package com.cagetech.cagetech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@IdClass(RutinaEjercicioId.class)
@Entity
@Table (name = "RutinasEjercicios")
public class RutinaEjercicioModel {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinasModel rutina;

    @Id
    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private EjercicioModel ejercicio;

    public RutinasModel getRutina() {
        return rutina;
    }

    public void setRutina(RutinasModel rutina) {
        this.rutina = rutina;
    }

    public EjercicioModel getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioModel ejercicio) {
        this.ejercicio = ejercicio;
    }
}