package com.example.userlogin.services;

import com.example.userlogin.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/*
 * FUNCIONALIDAD
 * Interfaz que define los métodos que se pueden realizar sobre la tabla User de la base de datos.
 */
public interface UserService extends UserDetailsService {

    // Registra un usuario en la base de datos
    User register(User user);

    // Encuentra un usuario basándose en el username
    Optional<User> findByUsername(String username);

    // Valida la contraseña de un usuario basándose en la pareja de información user y password
    boolean validatePassword(User user, String rawPassword);
}

