package com.cagetech.cagetech.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cagetech.cagetech.utils.DBUtils;

public class LoginController {

    @Autowired
    DBUtils dbUtils;

    String userMail;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Map<String, Object> model) {
        boolean validLogin = dbUtils.login(email, password);

        if (validLogin) return "redirect:/rutinas"; 
        else {
            model.put("error", "Email o contraseña incorrectos");
            return "login";
        }
    }

    @RequestMapping("/register")
    public String register() {
        return "registration";
    }
    
}
