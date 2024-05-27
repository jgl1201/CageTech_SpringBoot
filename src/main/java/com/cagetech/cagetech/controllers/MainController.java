package com.cagetech.cagetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String login() {
        return "Login";
    }

    @GetMapping("/login") 
    public String login2() {
        return "login";
    }    

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
