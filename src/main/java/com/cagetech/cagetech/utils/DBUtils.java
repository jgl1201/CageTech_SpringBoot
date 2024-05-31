package com.cagetech.cagetech.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    public DBConnection db;
    public Connection con;

    public boolean login(String email, String password) {
        db = new DBConnection("localhost", 3306, "root", "");
        con = db.getConnector();

        try {
            // Usar la BBDD correspondiente
            Statement stmDB = con.createStatement();
            String sql = "use cagetech";
            stmDB.execute(sql);

            // Seleccionar la contraseña del email introducido
            String psSQL = "select password from users where email = ?;";
            PreparedStatement ps = con.prepareStatement(psSQL);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString(0);
                return storedPassword.equals(password);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error en consulta de login");
            return false;
        }

        return false;
    }
    
}
