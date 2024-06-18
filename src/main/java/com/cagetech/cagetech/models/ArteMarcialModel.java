package com.cagetech.cagetech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;

@Entity
@Table(name = "ArtesMarciales")
public class ArteMarcialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Time calentamiento;
    private Time tecnica;
    private Time sparring;
    private Integer num_sparring;

    // Getters y Setters

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

    public Time getCalentamiento() {
        return calentamiento;
    }

    public void setCalentamiento(Time calentamiento) {
        this.calentamiento = calentamiento;
    }

    public Time getTecnica() {
        return tecnica;
    }

    public void setTecnica(Time tecnica) {
        this.tecnica = tecnica;
    }

    public Time getSparring() {
        return sparring;
    }

    public void setSparring(Time sparring) {
        this.sparring = sparring;
    }

    public Integer getNum_sparring() {
        return num_sparring;
    }

    public void setNum_sparring(Integer num_sparring) {
        this.num_sparring = num_sparring;
    }
}