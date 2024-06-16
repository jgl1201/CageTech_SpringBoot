package com.cagetech.cagetech.models;

import java.io.Serializable;
import java.util.Objects;

public class RutinaEjercicioId implements Serializable {

    private Integer rutina;
    private Integer ejercicio;

    public RutinaEjercicioId() {}

    public RutinaEjercicioId(Integer rutina, Integer ejercicio) {
        this.rutina = rutina;
        this.ejercicio = ejercicio;
    }

    public Integer getRutina() {
        return rutina;
    }

    public void setRutina(Integer rutina) {
        this.rutina = rutina;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    // Métodos equals y hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaEjercicioId that = (RutinaEjercicioId) o;
        return Objects.equals(rutina, that.rutina) &&
            Objects.equals(ejercicio, that.ejercicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rutina, ejercicio);
    }

    
    
}
