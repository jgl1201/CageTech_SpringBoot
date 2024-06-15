package com.cagetech.cagetech.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rutinas")
public class RutinasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "us_email")
    private UserModel userModel;

    @OneToMany(mappedBy = "rutina")
    private List<EjercicioModel> ejercicios;

    @OneToMany(mappedBy = "rutina")
    private List<ArteMarcialModel> artesMarciales;

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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<EjercicioModel> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioModel> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<ArteMarcialModel> getArtesMarciales() {
        return artesMarciales;
    }

    public void setArtesMarciales(List<ArteMarcialModel> artesMarciales) {
        this.artesMarciales = artesMarciales;
    }

}
