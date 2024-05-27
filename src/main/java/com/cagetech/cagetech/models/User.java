package com.cagetech.cagetech.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    private String email;
    private String nombre;
    private String apellidos;
    private String contrasena;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ejercicio> ejercicios;
    private Set<ArteMarcial> artesMarciales;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<Ejercicio> getEjercicios() {
        return ejercicios;
    }
    public void setEjercicios(Set<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Set<ArteMarcial> getArtesMarciales() {
        return artesMarciales;
    }
    public void setArtesMarciales(Set<ArteMarcial> artesMarciales) {
        this.artesMarciales = artesMarciales;
    }
}
