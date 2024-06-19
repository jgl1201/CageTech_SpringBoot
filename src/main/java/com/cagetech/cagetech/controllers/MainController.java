package com.cagetech.cagetech.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cagetech.cagetech.models.RutinasModel;
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
    public String rutinas(Model model) {
        System.out.println(userMail);
        System.out.println(utils.rutinasPorEmail(userMail).size());

        for (RutinasModel rutina : utils.rutinasPorEmail(userMail)) System.out.println(rutina.getId());

        if (userMail != null) model.addAttribute("rutinasUsuario", utils.rutinasPorEmail(userMail));

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

    @PostMapping("/rutinas/nueva")
    public String nuevaRutina(@RequestParam("nombre") String nombre) {
        utils.insertarRutina(userMail, nombre);

        return "redirect:/rutinas";
    }

    @RequestMapping("/rutinas/{id}")
    public String rutinaDetalles(@PathVariable Integer id, Model model) {
        System.out.println("===================================================");
        System.out.println("Mail guardado =====" + userMail);
        System.out.println("Ejercicios en la rutina =====" + utils.ejerciciosPorRutina(id).size());
        System.out.println("Artes marciales de la rutina ===== " + utils.artesMarcialesPorRutina(id).size());

        model.addAttribute("ejerciciosRutina", utils.ejerciciosPorRutina(id));
        model.addAttribute("artesMarcialesRutina", utils.artesMarcialesPorRutina(id));

        return "detalleRutina";
    }

    @RequestMapping("/ajustes")
    public String ajustes () {
        return "ajustes";
    }

    @RequestMapping("/perfil")
    public String perfil(Model model) {
        model.addAttribute("user", utils.datosPerfil(userMail));

        return "perfil";
    }

    @PostMapping("/perfil/update")
    public String updateDatosPerfil(@RequestParam("altura") Float altura, @RequestParam("peso") Float peso) {

        utils.actualizarPesoAltura(userMail, peso, altura);
        return "redirect:/perfil";

    }

}