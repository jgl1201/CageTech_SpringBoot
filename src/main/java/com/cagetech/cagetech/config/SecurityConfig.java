package com.cagetech.cagetech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/register", "/styles/**", "/scripts/**").permitAll() // Permitir acceso a estas rutas sin autenticación
                .anyRequest().authenticated() // Requerir autenticación para todas las demás rutas
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/rutinas", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
}