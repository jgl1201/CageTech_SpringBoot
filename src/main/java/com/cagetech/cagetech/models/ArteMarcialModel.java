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
@Table(name = "Artesmarciales")
public class ArteMarcialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private LocalTime calentamiento;
    private LocalTime tecnica;
    private LocalTime sparring;
    private Integer num_sparring;

    @ManyToOne
    @JoinColumn(name = "us_email")
    private UserModel userModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNum_sparring() {
        return num_sparring;
    }

    public void setNum_sparring(int num_sparring) {
        this.num_sparring = num_sparring;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
