package com.cagetech.cagetech.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cagetech.cagetech.models.ArteMarcialModel;
import com.cagetech.cagetech.models.EjercicioModel;
import com.cagetech.cagetech.models.RutinasModel;
import com.cagetech.cagetech.repositories.RutinasRepository;
import com.cagetech.cagetech.repositories.UserRepository;

@Component
public class Utils {

    @Autowired
    private DBConnection dbConnection;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RutinasRepository rutinasRepository;

    public boolean validateLogin(String email, String password) {
        Connection con = dbConnection.getConnector();

        try {
            String query = "SELECT * FROM users WHERE email = ? AND contrasena = ?;";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateRegistration (String email, String nombre, String apellidos, String password) {
        Connection con = dbConnection.getConnector();

        try {
            if(!userRepository.existsById(email)) {
                String query = "INSERT INTO users (email, nombre, apellidos, contrasena) VALUES (?, ?, ?, ?);";
                
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, email);
                pst.setString(2, nombre);
                pst.setString(3, apellidos);
                pst.setString(4, password);
                
                pst.executeUpdate();

                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertarRutina (String email, String nombre) {
        Connection con = dbConnection.getConnector();

        try {
            String query = "insert into rutinas (nombre, us_email) values (?, ?);";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, nombre);
            pst.setString(2, email);

            pst.executeUpdate();

            System.out.println("Insercion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Insercion fallida");
        }
    }
    
    public List<RutinasModel> rutinasPorEmail (String email) {
        List<RutinasModel> rutinasEmail = new ArrayList<>();

        List<RutinasModel> rutinas = (List<RutinasModel>) rutinasRepository.findAll();

        for (RutinasModel rutina : rutinas) 
            if (rutina.getUserModel().getEmail().equalsIgnoreCase(email)) rutinasEmail.add(rutina);

        return rutinasEmail;
    }

    public List<EjercicioModel> ejerciciosPorRutina (Integer idRutina) {
        Connection con = dbConnection.getConnector();
        
        List<EjercicioModel> ejerciciosRutina = new ArrayList<>();

        try {
            String query = "SELECT ej.nombre, ej.series, ej.repeticiones, ej.peso " + 
                            "from ejercicios ej " + 
                            "join rutinasejercicios rej on ej.id = rej.ejercicio_id " + 
                            "join rutinas r on r.id = rej.rutina_id " + 
                            "where r.id = ?;";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idRutina);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                EjercicioModel ejercicio = new EjercicioModel();
                ejercicio.setNombre(rs.getString("ej.nombre"));
                ejercicio.setSeries(rs.getInt("ej.series"));
                ejercicio.setRepeticiones(rs.getInt("ej.repeticiones"));
                ejercicio.setPeso(rs.getFloat("ej.peso"));

                ejerciciosRutina.add(ejercicio);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return ejerciciosRutina;
    }

    public List<ArteMarcialModel> artesMarcialesPorRutina (Integer rutinaId) {
        Connection con = dbConnection.getConnector();
        
        List<ArteMarcialModel> artesMarcialesRutina = new ArrayList<>();

        try {
            String query = "select am.nombre, am.calentamiento, am.tecnica, am.sparring, am.num_sparring "
                            + "from artesmarciales am " 
                            + "join rutinasartesmarciales ram on am.id = ram.arte_marcial_id "
                            + "join rutinas r on r.id = ram.rutina_id "
                            + "where r.id = ?;";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, rutinaId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ArteMarcialModel arteMarcial = new ArteMarcialModel();
                arteMarcial.setNombre(rs.getString("am.nombre"));
                arteMarcial.setCalentamiento(rs.getTime("am.calentamiento"));
                arteMarcial.setTecnica(rs.getTime("am.tecnica"));
                arteMarcial.setSparring(rs.getTime("am.sparring"));
                arteMarcial.setNum_sparring(rs.getInt("am.num_sparring"));

                artesMarcialesRutina.add(arteMarcial);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return artesMarcialesRutina;
    }

}