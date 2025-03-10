package com.example.userlogin.controllers;

import com.example.userlogin.models.User;
import com.example.userlogin.security.JwtUtil;
import com.example.userlogin.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
/*
 * FUNCIONALIDAD
 * Controlador el registro de nuevos usuarios y la correspondiente autenticación.
 * Aquí se generan los tokens JWT que permitirán solo a los usuarios autenticados acceder a las diferentes
 * funcionalidades de la aplicación de forma segura y respetando la privacidad de los datos agenos.
 */
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Registro de nuevos usuarios
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ResponseEntity.ok("User and default profile created successfully with ID: " + registeredUser.getId());
    }

    // Autenticación de usuarios
// AuthController.java
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        // Comprobación de los parámetros recibidos
        logger.info("Login attempt:");
        logger.info("Received username: {}", username);
        logger.info("Received password: {}", password);

        // Comprobación de que se reciben los parámetros necesarios
        if (username == null || password == null) {
            String missingParams = "";
            if (username == null) {
                missingParams += "username ";
            }
            if (password == null) {
                missingParams += "password";
            }
            logger.error("Missing request parameters: {}", missingParams);
            return ResponseEntity.badRequest().body("Missing request parameters: " + missingParams);
        }

        // Comprobación de que el usuario existe y la contraseña es correcta.
        // Si es así, se genera un token JWT.
        return userService.findByUsername(username)
                .map(user -> {
                    if (userService.validatePassword(user, password)) {
                        String token = jwtUtil.generateToken(user.getUsername());
                        logger.info("Generated JWT Token: {}", token); // Imprime el token en la terminal
                        return ResponseEntity.ok().body(Collections.singletonMap("token", token)); // Envía el token en la respuesta
                    } else {
                        return ResponseEntity.status(401).body("Invalid credentials");
                    }
                })
                .orElseGet(() -> {
                    return ResponseEntity.status(401).body("Invalid credentials");
                });
    }
}