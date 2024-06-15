package com.cagetech.cagetech.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cagetech.cagetech.repositories.UserRepository;

@Component
public class Utils {

    @Autowired
    private DBConnection dbConnection;
    @Autowired
    private UserRepository userRepository;

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
    
}
