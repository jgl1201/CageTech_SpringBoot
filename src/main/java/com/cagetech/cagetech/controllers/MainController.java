package com.cagetech.cagetech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cagetech.cagetech.utils.Utils;


@Controller
public class MainController {

    @Autowired
    private Utils utils;
    private String userMail;

    // Login    
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    // Register
    @RequestMapping("/register")
    public String register() {
        return "registration";
    }

    // Rutinas 
    @RequestMapping("/rutinas")
    public String rutinas() {
        return "rutinas";
    }

    // Load rutinas.html from login form    
    @PostMapping("/rutinas/login")
    public String rutinasLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
        userMail = email;

        if (utils.validateLogin(email, password)) return "redirect:/rutinas";
        return "redirect:/";
    }

    @PostMapping("/rutinas/register")
    public String rutinasRegister (@RequestParam("email") String email, @RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("password") String password) {
        userMail = email;

        if (utils.validateRegistration(email, nombre, apellidos, password)) return "redirect:/rutinas";
        return "redirect:/register";
    }
        
}