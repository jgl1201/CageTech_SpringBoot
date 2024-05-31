package com.cagetech.cagetech.utils;

import java.sql.Connection;
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

            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error en consulta de login");
            return false;
        }

        return true;
    }
    
}
