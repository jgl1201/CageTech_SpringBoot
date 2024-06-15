package com.cagetech.cagetech.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cagetech.cagetech.utils.DBConnection;


@Controller
public class MainController {

    @Autowired
    private DBConnection dbConnection;

    /** Login actions */
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login2() {
        return "login";
    }

    @PostMapping("/rutinas")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Connection con = dbConnection.getConnector();
        ModelAndView modelAndView = new ModelAndView();

        try {
            String query = "select * from users where email = ? and contrasena = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next() ) modelAndView.setViewName("rutinas");
            else {
                modelAndView.setViewName("login");
                modelAndView.addObject("error", "Email o contraseña incorrectos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Error en la conexion");
        }

        return modelAndView;
    }

    @RequestMapping("/register")
    public String register() {
        return "registration";
    }

}
