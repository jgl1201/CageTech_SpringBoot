package com.cagetech.cagetech.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cagetech.cagetech.models.ArteMarcial;
import com.cagetech.cagetech.models.Ejercicio;
import com.cagetech.cagetech.models.User;
import com.cagetech.cagetech.repositories.ArteMarcialRepository;
import com.cagetech.cagetech.repositories.EjercicioRepository;
import com.cagetech.cagetech.repositories.UserRepository;

// Controlador para guardar ejercicios en la BBDD
@RestController
@RequestMapping("/ejercicios")
public class EjercicioController {
    
    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private ArteMarcialRepository arteMarcialRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/ejercicio")
    public Ejercicio saveEjercicio (@AuthenticationPrincipal UserDetails userDetails, @RequestBody Ejercicio ejercicio) {
        String userName = userDetails.getUsername();

        // Buscar el usuario en la base de datos y lanzar una excepción si no se encuentra
        User user = userRepository.findById(userName)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<User> optionalUser = Optional.of(user);

        // Asignar el usuario al ejercicio
        ejercicio.setUser(optionalUser);

        // Guardar el ejercicio en la base de datos y devolverlo
        return ejercicioRepository.save(ejercicio);
    }

    @PostMapping("/artesmarciales")
    public ArteMarcial saveArteMarcial (@AuthenticationPrincipal UserDetails userDetails, @RequestBody ArteMarcial arteMarcial) {
        String userName = userDetails.getUsername();

        User user = userRepository.findById(userName)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<User> optionalUser = Optional.of(user);

        arteMarcial.setUser(optionalUser);

        return arteMarcialRepository.save(arteMarcial);
    }
}
