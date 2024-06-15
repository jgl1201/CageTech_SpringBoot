package com.cagetech.cagetech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login2() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "registration";
    }

}
