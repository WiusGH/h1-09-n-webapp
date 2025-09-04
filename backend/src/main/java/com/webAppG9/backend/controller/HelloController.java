package com.webAppG9.backend.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.repository.UserRepository;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/prueba")
    public ResponseEntity<?> prueba() {
        Optional<User> user = userRepository.findByEmail("claudio@alura.com");

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // devuelve el usuario en JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Usuario no encontrado")); // devuelve error en JSON
        }

    }

    // @GetMapping("/prueba")
    // public String hola() {
    // return userRepository.findByUsername("Juan")
    // .map(user -> "Usuario encontrado: " + user.getUsername())
    // .orElse("Usuario no encontrado");
    // }

}