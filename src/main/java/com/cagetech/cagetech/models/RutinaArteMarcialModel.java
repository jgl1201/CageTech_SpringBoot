package com.cagetech.cagetech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@IdClass(RutinaArtemarcialId.class)
@Entity
@Table(name = "RutinasArtesMarciales")
public class RutinaArteMarcialModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private RutinasModel rutina;

    @Id
    @ManyToOne
    @JoinColumn(name = "arte_marcial_id")
    private ArteMarcialModel arteMarcial;

    public RutinasModel getRutina() {
        return rutina;
    }

    public void setRutina(RutinasModel rutina) {
        this.rutina = rutina;
    }

    public ArteMarcialModel getArteMarcial() {
        return arteMarcial;
    }

    public void setArteMarcial(ArteMarcialModel arteMarcial) {
        this.arteMarcial = arteMarcial;
    }
}