package com.cagetech.cagetech.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connector;

    public DBConnection (String host, int port, String user, String password) {
        this.connector = connect(host, port, user, password);
    }

    public Connection connect(String host, int port, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conecion establecida");
            return con;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Error de conexion");
            return null;
        }
    }

    public Connection getConnector() {
        return this.connector;
    }
}
