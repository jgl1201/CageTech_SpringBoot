package com.cagetech.cagetech.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {

    private Connection connector;

    public DBConnection(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String user,
            @Value("${spring.datasource.password}") String password) {
        this.connector = connect(url, user, password);
    }

    private Connection connect(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al conectar");
            return null;
        }
    }

    public Connection getConnector() {
        return this.connector;
    }
}
