package com.cagetech.cagetech.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cagetech.cagetech.models.User;
import com.cagetech.cagetech.repositories.UserRepository;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Página de inicio
    @GetMapping("/")
    public String showLoginPage() {
        return "Login";
    }

    // Validación de usuario existente
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        Optional<User> userOpt = userRepository.findById(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getContrasena())) {
                return "rutinas";
            }
        }

        return "login";
    }

    
    // Página de registro
    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    // Crear un nuevo usuario
    @PostMapping("/registration")
    public String register (@RequestParam String email, @RequestParam String nombre, @RequestParam String apellidos, @RequestParam String contrasena) {
        if (userRepository.existsById(email)) return "registration";

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setNombre(nombre);
        newUser.setApellidos(apellidos);
        newUser.setContrasena(passwordEncoder.encode(contrasena));

        userRepository.save(newUser);

        return "rutinas";
    }

}