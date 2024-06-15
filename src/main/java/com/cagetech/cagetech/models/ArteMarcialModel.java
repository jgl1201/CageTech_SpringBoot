package com.cagetech.cagetech.models;

import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ArtesMarciales")
public class ArteMarcialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private LocalTime calentamiento;
    private LocalTime tecnica;
    private LocalTime sparring;
    private Integer numSparring;

    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinasModel rutina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getCalentamiento() {
        return calentamiento;
    }

    public void setCalentamiento(LocalTime calentamiento) {
        this.calentamiento = calentamiento;
    }

    public LocalTime getTecnica() {
        return tecnica;
    }

    public void setTecnica(LocalTime tecnica) {
        this.tecnica = tecnica;
    }

    public LocalTime getSparring() {
        return sparring;
    }

    public void setSparring(LocalTime sparring) {
        this.sparring = sparring;
    }

    public Integer getNumSparring() {
        return numSparring;
    }

    public void setNumSparring(Integer numSparring) {
        this.numSparring = numSparring;
    }

    public RutinasModel getRutina() {
        return rutina;
    }

    public void setRutina(RutinasModel rutina) {
        this.rutina = rutina;
    }

}
