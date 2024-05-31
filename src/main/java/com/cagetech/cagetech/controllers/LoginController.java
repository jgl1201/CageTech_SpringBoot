package com.cagetech.cagetech.controllers;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cagetech.cagetech.utils.DBConnection;

public class LoginController {

    @Autowired
    DBConnection dbConnection;

    @Autowired
    Connection connection;

    String userMail;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password) {
        dbConnection = new DBConnection("localhost",  306, "root", "");
        connection = dbConnection.getConnection();

        boolean validUser = 
    }
    
}
