package com.cagetech.cagetech.models;

import java.io.Serializable;
import java.util.Objects;

public class RutinaArtemarcialId implements Serializable {

    private Integer rutina;
    private Integer arteMarcial;

    public RutinaArtemarcialId() {}

    public RutinaArtemarcialId(Integer rutina, Integer arteMarcial) {
        this.rutina = rutina;
        this.arteMarcial = arteMarcial;
    }

    public Integer getRutina() {
        return rutina;
    }

    public void setRutina(Integer rutina) {
        this.rutina = rutina;
    }

    public Integer getArteMarcial() {
        return arteMarcial;
    }

    public void setArteMarcial(Integer arteMarcial) {
        this.arteMarcial = arteMarcial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaArtemarcialId that = (RutinaArtemarcialId) o;
        return Objects.equals(rutina, that.rutina) && Objects.equals(arteMarcial, that.arteMarcial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rutina, arteMarcial);
    }
    
}
