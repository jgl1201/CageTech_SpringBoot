package com.cagetech.cagetech.models;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ArteMarcial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String calentamiento;
    private String tecnica;
    private String sparring;
    private String num_sparring;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private Optional<User> user;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String getCalentamiento() {
        return calentamiento;
    }

    public void setCalentamiento(String calentamiento) {
        this.calentamiento = calentamiento;
    }


    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }


    public String getSparring() {
        return sparring;
    }

    public void setSparring(String sparring) {
        this.sparring = sparring;
    }


    public String getNum_sparring() {
        return num_sparring;
    }

    public void setNum_sparring(String num_sparring) {
        this.num_sparring = num_sparring;
    }


    public Optional<User> getUser() {
        return user;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }
}
