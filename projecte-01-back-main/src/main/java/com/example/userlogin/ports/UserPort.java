package com.example.userlogin.ports;

import com.example.userlogin.models.User;

import java.util.List;
import java.util.Optional;

/*
 * FUNCIONALIDAD
 * Interfaz que define los métodos que se pueden realizar sobre la entidad User.
 * El uso de este puerto permite desacoplar la lógica de negocio de la lógica de persistencia en lo relacionado
 * con la entidad User de la base de datos.
 */
public interface UserPort {
    // Método que permite buscar un usuario por su nombre de usuario.
    Optional<User> findByUsername(String username);

    // Método para guardar la persistencia del usuario.
    User saveUser(User user);
}

